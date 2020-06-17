import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.awt.BorderLayout
import java.awt.Button
import java.awt.Container
import java.awt.Dimension
import java.awt.event.ActionListener
import java.io.IOException
import javax.swing.JFrame
import javax.swing.JLabel


fun main() {
    val frame = JFrame("COVID Scanner")
    val c = frame.contentPane
    val COVID = JLabel("Reloading")
    label(c, COVID)
    val button = Button("[ RELOAD ]")
    button.addActionListener(ActionListener { _ ->
        label(c, COVID)
    })
    c.add(button, BorderLayout.SOUTH)
    frame.setLocation(500, 500)
    frame.preferredSize = Dimension(740, 100)
    frame.pack()
    frame.isVisible = true
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
}

fun label(c: Container, COVID: JLabel) {
    val url = "https://www.worldometers.info/coronavirus/"
    var doc: Document? = null
    try {
        doc = Jsoup.connect(url).get()
    } catch (e: IOException) {
        e.printStackTrace()
    }
    val element: Elements? = doc?.select("div.maincounter-number")
    val span: String = element!!.select("span").html().replace("\n", "|")
    val s = span.split("|").toTypedArray()
    COVID.text = "Coronavirus Cases : " + s[0] + " | Deaths : " + s[1] + " | Recovered : " + s[2]
    COVID.font = COVID.font.deriveFont(20.0f)
    c.add(COVID)
}
