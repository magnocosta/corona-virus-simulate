package msat.view.animation

import java.awt.Component
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.Timer
import kotlin.random.Random

class SimplePingPongAnimation(private val component: Component, private val container: Component) : ActionListener {

    companion object {
        const val FRAME_RATE = 1
    }

    enum class DirectionX { LEFT, RIGHT }
    enum class DirectionY { UP, DOWN }

    private val timer = Timer(5, this)
    private var directionX = if (Random.nextBoolean()) DirectionX.LEFT else DirectionX.RIGHT
    private var directionY = if (Random.nextBoolean()) DirectionY.UP else DirectionY.DOWN

    init {
        timer.start()
    }

    override fun actionPerformed(event: ActionEvent?) {

        val newX = newPositionX()
        val newY = newPositionY()

        component.setLocation(newX, newY)
        component.repaint()
    }

    private fun newPositionX(): Int {
        if (component.x >= container.width) {
            directionX = DirectionX.LEFT
        }

        if (component.x <= 0) {
            directionX = DirectionX.RIGHT
        }

        return if (directionX == DirectionX.RIGHT) component.x + FRAME_RATE else component.x - FRAME_RATE
    }

    private fun newPositionY(): Int {
        if (component.y >= container.height) {
            directionY = DirectionY.UP
        }

        if (component.y <= 0) {
            directionY = DirectionY.DOWN
        }

        return if (directionY == DirectionY.DOWN) component.y + FRAME_RATE else component.y - FRAME_RATE
    }

}