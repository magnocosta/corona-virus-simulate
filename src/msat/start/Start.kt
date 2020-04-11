package msat.start

import msat.view.MainView
import javax.swing.UIManager

fun main() {
    UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel")
    MainView()
}