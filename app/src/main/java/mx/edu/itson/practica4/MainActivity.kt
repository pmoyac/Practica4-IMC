package mx.edu.itson.practica4

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val pesoK:EditText=findViewById(R.id.etKilos)
        val alturaE:EditText=findViewById(R.id.etEstatura)
        val calcular: Button =findViewById(R.id.btnCalcular)
        val imc:TextView=findViewById(R.id.tvIMC)
        val rango:TextView=findViewById(R.id.tvRange)

        calcular.setOnClickListener {
            var peso:Double=0.0
            var estatura:Double=0.0
            try {
                peso=pesoK.text.toString().toDouble()
                estatura=alturaE.text.toString().toDouble()
            }catch (e:java.lang.Exception){
                imc.setText("Debe ingresar valores reales")
                print(e)
            }

            fun calcularIMC(kilos:Double,altura:Double):Double{
                return kilos/(altura*altura)
            }

            var resultado = calcularIMC(peso, estatura)
            val formattedNumber="%.2f".format(resultado)
            imc.setText(formattedNumber)
            var salud:String
            var color:Int

            when{
                resultado < 18.5 -> {
                    salud= "Bajo Peso"
                    color = R.color.colorRed
                }
                resultado >= 18.5 && resultado <= 24.9 -> {
                    salud= "Saludable"
                    color = R.color.colorGreenish
                }
                resultado >= 25 && resultado <= 29.9 -> {
                    salud= "Sobrepeso"
                    color = R.color.colorYellow
                }
                resultado >= 30 && resultado <= 34.9 -> {
                    salud= "Obesidad Grado 1"
                    color = R.color.colorOrange
                }
                resultado >= 35 && resultado <= 39.9 -> {
                    salud= "Obesidad Grado 2"
                    color = R.color.colorBrowm
                }
                resultado >= 40 -> {
                    salud = "Obesidad Grado 3"
                    color = R.color.colorRed
                }
                else -> {
                    salud = "Error"
                    color = 0
                }
            }

            rango.setBackgroundResource(color)
            rango.setText(salud)

        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}