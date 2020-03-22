package msat.view.component

import java.awt.Color
import java.awt.Graphics
import javax.swing.JPanel
import kotlin.random.Random

class Ball(private val color: Color, private val maxWidth: Int, private val maxHeight: Int) : JPanel() {

    companion object {
        const val WIDTH = 20
        const val HEIGHT = 20
    }

    private val positionX = Random.nextInt(0, maxWidth - WIDTH)
    private val positionY = Random.nextInt(0, maxHeight - HEIGHT)

    init {
        setSize(WIDTH, HEIGHT)
        setLocation(positionX, positionY)
        isOpaque = false
    }

    override fun paintComponent(graphics: Graphics) {
        super.paintComponent(graphics)
        graphics.color = color
        graphics.fillOval(0, 0, WIDTH, HEIGHT)
    }

    override fun toString(): String {
        val limitValue = "{width: $maxWidth, height: $maxHeight}"
        return "Ball:{ " +
                "x: $positionX, " +
                "y: $positionY, " +
                "width: $WIDTH, " +
                "height: $HEIGHT, " +
                "limits: $limitValue " +
                "}"
    }

}