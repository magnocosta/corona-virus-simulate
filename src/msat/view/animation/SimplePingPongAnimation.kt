package msat.view.animation

import msat.infra.Logger
import java.awt.Component
import java.awt.Point
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

    private enum class DirectionX { LEFT, RIGHT }
    private enum class DirectionY { UP, DOWN }

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

        moveObject()
    }

    private fun moveObject() {
        val point = calculateNewPositionOfElement()
        target.setLocation(point.x, point.y)
        target.repaint()
    }

    private fun calculateNewPositionOfElement(): Point {
        val x = newPositionX()
        val y = newPositionY()

        val point = Point(x, y)

        if (hasCollisionWithOtherObject(point)) {
            logger.debug("Collision detected on $target")
            val x = newPositionX(true)
            val y = newPositionY(true)
            return Point(x, y)
        }

        return point
    }

    private fun setDirectionXByReverse() {
        directionX = if (directionX == DirectionX.RIGHT)
            DirectionX.LEFT
        else
            DirectionX.RIGHT
    }

    private fun setDirectionXByLimits() {
        if (target.x >= container.width) {
            directionX = DirectionX.LEFT
        }

        if (target.x <= 0) {
            directionX = DirectionX.RIGHT
        }
    }

    private fun newPositionX(useReverseMode: Boolean = false): Int {
        if (useReverseMode)
            setDirectionXByReverse()
        else
            setDirectionXByLimits()

        return if (directionX == DirectionX.RIGHT) target.x + FRAME_RATE else target.x - FRAME_RATE
    }

    private fun setDirectionYByLimits() {
        if (target.y >= container.height) {
            directionY = DirectionY.UP
        }

        if (target.y <= 0) {
            directionY = DirectionY.DOWN
        }
    }

    private fun setDirectionYByReverse() {
        directionY = if (directionY == DirectionY.UP)
            DirectionY.DOWN
        else
            DirectionY.UP
    }

    private fun newPositionY(reverseMode: Boolean = false): Int {
        if (reverseMode)
            setDirectionYByReverse()
        else
            setDirectionYByLimits()

        return if (directionY == DirectionY.DOWN) target.y + FRAME_RATE else target.y - FRAME_RATE
    }

    private fun hasCollisionWithOtherObject(point: Point): Boolean {
        val rectangle = Rectangle(point.x, point.y, target.width, target.height)
        return objectsOnContainer.any { rectangle.intersects(it.bounds) }
    }

}