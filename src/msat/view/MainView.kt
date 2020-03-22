package msat.view

import msat.infra.Logger
import msat.model.Person
import msat.model.Population
import msat.view.converter.drawAsBall
import java.awt.BorderLayout
import java.awt.Color
import javax.swing.BorderFactory
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JPanel

class MainView: JFrame() {

    /******************************************************************************************************************
     *** View Config
     ******************************************************************************************************************/
    private val windowMaxWidth = 800
    private val windowMaxHeight = 800
    private val populationSize = 100
    private val windowTitle = "Corona Virus Simulator"

    /******************************************************************************************************************
     *** View Components
     ******************************************************************************************************************/
    private val bttNewSimulation = JButton("New Simulation")
    private val bttClearSimulation = JButton("Clear Simulation")
    private val pnContainer = JPanel(BorderLayout())
    private val pnMain = JPanel(null)

    /******************************************************************************************************************
     *** Others Stuffs
     ******************************************************************************************************************/
    private val logger = Logger
    private var listOfBalls = mutableListOf<Person>()

    init {
        configView()
    }

    private fun configView() {
        configFrame()
        configPanels()
        configButtons()
        drawPopulationOnView(Population(populationSize))
    }

    private fun configFrame() {
        defaultCloseOperation = DISPOSE_ON_CLOSE
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
        bttNewSimulation.addActionListener {
            clearPopulationOnView()
            drawPopulationOnView(Population(300))
        }
        bttClearSimulation.addActionListener { clearPopulationOnView() }
        pnContainer.add(bttNewSimulation, BorderLayout.NORTH)
        pnContainer.add(bttClearSimulation, BorderLayout.SOUTH)
    }

    private fun drawPopulationOnView(population: Population) {
        population.people.forEach {
            val view = it.drawAsBall(it, this.width, this.height)
            pnMain.add(view)
        }
        reDrawView()
    }

    private fun clearPopulationOnView() {
        pnMain.components.filterIsInstance<JPanel>().forEach { pnMain.remove(it) }
        reDrawView()
    }

    private fun reDrawView() {
        pnMain.revalidate()
        pnMain.repaint()
    }

}