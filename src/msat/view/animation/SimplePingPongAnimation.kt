package msat.view.animation

import msat.infra.Logger
import java.awt.Component
import java.awt.Rectangle
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JPanel
import javax.swing.Timer
import kotlin.random.Random

class SimplePingPongAnimation(private val target: Component, private val container: JPanel) : ActionListener {

    companion object {
        const val FRAME_RATE = 1
    }

    enum class DirectionX { LEFT, RIGHT }
    enum class DirectionY { UP, DOWN }

    private val logger = Logger
    private val timer = Timer(5, this)
    private val objectsOnContainer = container.components.filterIsInstance<JPanel>().filter { it != target }

    private var directionX = if (Random.nextBoolean()) DirectionX.LEFT else DirectionX.RIGHT
    private var directionY = if (Random.nextBoolean()) DirectionY.UP else DirectionY.DOWN

    init {
        timer.start()
    }

    override fun actionPerformed(event: ActionEvent?) {

        if (!target.isVisible) {
            logger.debug("Stopping animation, because object is not visible $target")
            timer.stop()
            return
        }

        val newX = newPositionX()
        val newY = newPositionY()

        if (hasCollisionWithOtherObject(newX, newY)) {
            logger.debug("Collision detected on $target")
        }

        target.setLocation(newX, newY)
        target.repaint()

//        logger.debug("Object moved to new position $target")
    }

    private fun newPositionX(): Int {
        if (target.x >= container.width) {
            directionX = DirectionX.LEFT
        }

        if (target.x <= 0) {
            directionX = DirectionX.RIGHT
        }

        return if (directionX == DirectionX.RIGHT) target.x + FRAME_RATE else target.x - FRAME_RATE
    }

    private fun newPositionY(): Int {
        if (target.y >= container.height) {
            directionY = DirectionY.UP
        }

        if (target.y <= 0) {
            directionY = DirectionY.DOWN
        }

        return if (directionY == DirectionY.DOWN) target.y + FRAME_RATE else target.y - FRAME_RATE
    }

    private fun hasCollisionWithOtherObject(newX: Int, newY: Int): Boolean {
        val rectangle = Rectangle(newX, newY, target.width, target.height)
        return objectsOnContainer.any { rectangle.intersects(it.bounds) }
    }

}