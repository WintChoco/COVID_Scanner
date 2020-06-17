import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.awt.BorderLayout
import java.awt.Button
import java.awt.Dimension
import java.awt.event.ActionListener
import java.io.IOException
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel


fun main() {
    val frame = JFrame("COVID Scanner")
    load(frame)
}

fun load(frame: JFrame) {
    println("로드되었습니다!")
    val c = frame.contentPane
    val url = "https://www.worldometers.info/coronavirus/"
    var doc: Document? = null
    try {
        doc = Jsoup.connect(url).get()
    } catch (e: IOException) {
        e.printStackTrace()
    }
    val element: Elements? = doc?.select("div.maincounter-number")
    val span: String = element!!.select("span").html().replace("\n", "|")
    println(span)
    val s = span.split("|").toTypedArray()
    val COVID = JLabel("Coronavirus Cases : " + s[0] + " | Deaths : " + s[1] + " | Recovered : " + s[2])
    COVID.font = COVID.font.deriveFont(20.0f)
    c.add(COVID)
    val button = Button("[ RELOAD ]")
    button.addActionListener(ActionListener { e ->
        JFrame.EXIT_ON_CLOSE
        load(frame)
    })
    c.add(button, BorderLayout.SOUTH)
    frame.setLocation(0, 0)
    frame.preferredSize = Dimension(740, 100)
    frame.pack()
    frame.isVisible = true
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
}