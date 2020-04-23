package msat.view

import msat.infra.Logger
import msat.model.Population
import msat.view.animation.SimplePingPongAnimation
import msat.view.component.Ball
import msat.view.converter.drawAsBall
import java.awt.BorderLayout
import java.awt.Color
import java.awt.GridLayout
import javax.swing.*

class MainView : JFrame("Tela Principal") {

    /******************************************************************************************************************
     *** View Config
     ******************************************************************************************************************/
    private val windowMaxWidth = 800
    private val windowMaxHeight = 800
    private val windowTitle = "Corona Virus Simulator"

    /******************************************************************************************************************
     *** Containers View Components
     ******************************************************************************************************************/
    private val pnContainer = JPanel(BorderLayout())
    private val pnMenu = JPanel(GridLayout(1, 4))
    private val pnMain = JPanel(null)
    private val pnFooter = JPanel(null)

    /******************************************************************************************************************
     *** Container: Menu
     ******************************************************************************************************************/
    private val bttNewSimulation = JButton("New Simulation")
    private val bttClearSimulation = JButton("Clear Simulation")
    private val bttConfigSimulation = JButton("Config Simulation")
    private val bttResultSimulation = JButton("Result Simulation")

    /******************************************************************************************************************
     *** Others Stuffs
     ******************************************************************************************************************/
    private val logger = Logger
    private var population = Population(0)

    init {
        configView()
    }

    private fun configView() {
        configWindow()
        configMenu()
        configMain()
        configFooter()
    }

    private fun configWindow() {
        defaultCloseOperation = DISPOSE_ON_CLOSE
        title = windowTitle
        isVisible = true
        setSize(windowMaxWidth, windowMaxHeight)

        pnContainer.add(pnMenu, BorderLayout.NORTH)
        pnContainer.add(pnMain, BorderLayout.CENTER)
        pnContainer.add(pnFooter, BorderLayout.SOUTH)

        this.contentPane = pnContainer
    }

    private fun configMenu() {
        pnMenu.border = BorderFactory.createLineBorder(Color.RED)

        pnMenu.add(bttNewSimulation)
        pnMenu.add(bttClearSimulation)
        pnMenu.add(bttConfigSimulation)
        pnMenu.add(bttResultSimulation)

        bttNewSimulation.addActionListener {
            clearPopulationOnView()
            population = Population((area() / Ball.area()) / 4)
//            population = Population(2)
            drawPopulationOnView()
        }

        bttClearSimulation.addActionListener {
            clearPopulationOnView()
        }

        bttResultSimulation.addActionListener {
            val result = StringBuilder()
            result.append("Total: ${population.people.size}, ")
            result.append("Dead: ${population.deadPeopleInPercentage()}%, ")
            result.append("Cured: ${population.curedPeopleInPercentage()}%, ")
            result.append("Sick: ${population.sickPeopleInPercentage()}%, ")
            result.append("Not Affected: ${population.notAffectedPeopleInPercentage()}%")
            JOptionPane.showMessageDialog(this, result.toString())
        }
    }

    private fun configMain() {
        pnMain.border = BorderFactory.createLineBorder(Color.BLACK)
    }

    private fun configFooter() {
        pnFooter.border = BorderFactory.createLineBorder(Color.GREEN)
    }

    private fun drawPopulationOnView() {
        population.people.forEach {
            val view = it.drawAsBall(it, pnMain.width, pnMain.height)
            SimplePingPongAnimation(view, pnMain)
            pnMain.add(view)
        }
        reDrawView()
    }

    private fun clearPopulationOnView() {
        pnMain.components.filterIsInstance<JPanel>().forEach {
            pnMain.remove(it)
            it.isVisible = false
        }
        reDrawView()
    }

    private fun reDrawView() {
        pnMain.revalidate()
        pnMain.repaint()
    }

    private fun area(): Int {
        return pnMain.height * pnMain.width
    }

}