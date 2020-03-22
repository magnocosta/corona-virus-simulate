package model.msat.view

import msat.infra.Logger
import msat.model.Ball
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Point
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.*

class MainView: JFrame() {

    /******************************************************************************************************************
    *** View Config
    ******************************************************************************************************************/
    private val windowMaxWidth  = 800
    private val windowMaxHeight = 800
    private val windowTitle     = "Aprendendo sobre threads"

    /******************************************************************************************************************
     *** View Components
     ******************************************************************************************************************/
    private val bttStartThread  = JButton("Start Thread")
    private val bttStopThread   = JButton("Stop Thread")
    private val pnContainer     = JPanel(BorderLayout())
    private val pnMain          = JPanel(null)

    /******************************************************************************************************************
     *** Others Stuffs
     ******************************************************************************************************************/
    private val logger = Logger
    private var listOfBalls = mutableListOf<Ball>()

    init {
        configView()
    }

    private fun configView() {
        configFrame()
        configPanels()
        configButtons()
        population()
    }

    private fun configFrame() {
        defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE
        title = windowTitle
        isVisible = true
        setSize(windowMaxWidth, windowMaxHeight)
    }

    private fun configPanels() {
        pnMain.border = BorderFactory.createLineBorder(Color.black)
        pnContainer.add(pnMain)
        this.add(pnContainer)
    }

    private fun configButtons() {
        bttStartThread.addActionListener { countRedBalls() }
        pnContainer.add(bttStartThread, BorderLayout.NORTH)
        pnContainer.add(bttStopThread, BorderLayout.SOUTH)
    }

    private fun population() {
        isVisible = false
        for (i in 1..2) {
            createBall()
        }
        isVisible = true
    }

    private fun createBall() {
        val ball = Ball(this.width, this.height)
        pnMain.add(ball)
        animate(ball, Point(this.width, this.height), 44, 1000)
        listOfBalls.add(ball)
    }

    private fun countRedBalls() {
        val doentes = listOfBalls.filter { it.colorChoose == 0 }.count()
        val saudavel = listOfBalls.filter { it.colorChoose == 1 }.count()
        val curado = listOfBalls.filter { it.colorChoose == 2 }.count()
        val morto = listOfBalls.filter { it.colorChoose == 3 }.count()

        JOptionPane.showMessageDialog(this, "Mortos: $morto, curados: $curado, saudavel: $saudavel, doentes: $doentes")
    }

    private fun animate(component: JComponent, newPoint: Point, frames: Int, interval: Int) {
        val compBounds = component.bounds
        val oldPoint = Point(compBounds.x, compBounds.y)
        val animFrame = Point(
            (newPoint.x - oldPoint.x) / frames,
            (newPoint.y - oldPoint.y) / frames
        )
        Timer(interval, object : ActionListener {
            var currentFrame = 0
            override fun actionPerformed(e: ActionEvent) {
                component.setBounds(
                    oldPoint.x + animFrame.x * currentFrame,
                    oldPoint.y + animFrame.y * currentFrame,
                    compBounds.width,
                    compBounds.height
                )
                if (currentFrame != frames) currentFrame++ else (e.getSource() as Timer).stop()
            }
        }).start()
    }

}