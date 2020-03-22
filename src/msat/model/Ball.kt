package msat.model

import msat.infra.Logger
import java.awt.Color
import java.awt.Graphics
import javax.swing.JPanel
import kotlin.random.Random

class Ball(windowWidth: Int, windowHeight: Int): JPanel() {

    private val logger = Logger
    private val nameX = "Ball ${Random.nextInt(0, 100)}"
    private val colors = listOf(Color.RED, Color.YELLOW, Color.GREEN, Color.BLACK)

    private val squareW = 20
    private val squareH = 20

    public val colorChoose = Random.nextInt(0, colors.size)
    private val squareX = Random.nextInt(0, windowWidth - squareW)
    private val squareY = Random.nextInt(0, windowHeight - squareH)

    init {
        logger.debug("Object ball created { x: $squareX, limitX: $windowWidth, y: $squareY, limitY: $windowHeight }")
        setSize(squareW, squareH)
        setLocation(squareX, squareY)
        isOpaque = false
    }

    override fun paintComponent(graphics: Graphics) {
        super.paintComponent(graphics)
        graphics.color = colors[colorChoose]
        graphics.fillOval(0, 0, squareW, squareH)
    }

}