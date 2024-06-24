package com.example.projetoipo

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.graphics.text.LineBreaker
import android.os.Bundle
import android.provider.MediaStore
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.collection.lruCache
import androidx.core.content.contentValuesOf
import androidx.core.graphics.withTranslation
import com.example.projetoipo.databinding.ActivityResumoBinding
import java.io.IOException
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.Locale


class Resumo : AppCompatActivity() {

    private lateinit var binding: ActivityResumoBinding

    private var pdfDocument = PdfDocument()
    private var retornoCrbm = ""
    private var retornoObm = ""
    private var retornoGraduacaoNome = ""
    private var retornoData = ""
    private var retronoHora = ""
    private var retornoNatureza = ""
    private var retornoSubNatureza = ""
    private var retornoCidade = ""
    private var retornoLogradouro = ""
    private var retornoBairro = ""
    private var retornoComplemento = ""
    private var retornoCbAcionado = ""
    private var retornoViaturas = ""
    private var retornoEfetivo = ""
    private var comandoRegional = ""

    private var retornoVitIlsea: Int = 0
    private var retornoVitCod1: Int = 0
    private var retornoVitCod2: Int = 0
    private var retornoVitCod3: Int = 0
    private var retornoVitCod4: Int = 0
    private var retornoTotalVitimas: Int = 0
    private var retornoObsVitimas = ""

    private var retornoMeioAmbiente = ""
    private var retornoDanosPropriedade = ""
    private var retornoCenario = ""
    private var retornoDesdobramento = ""
    private var retornoApoio = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResumoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        retornoGraduacaoNome = intent.getStringExtra("graduacaoNome").toString()
        retornoCrbm = intent.getStringExtra("crbm").toString()
        retornoObm = intent.getStringExtra("obm").toString()
        retornoData = intent.getStringExtra("data").toString()
        retronoHora = intent.getStringExtra("hora").toString()
        retornoNatureza = intent.getStringExtra("natureza").toString()
        retornoSubNatureza = intent.getStringExtra("subNatureza").toString()
        retornoCidade = intent.getStringExtra("cidade").toString()
        retornoLogradouro = intent.getStringExtra("logradouro").toString()
        retornoBairro = intent.getStringExtra("bairro").toString()
        retornoComplemento = intent.getStringExtra("complemento").toString()
        retornoCbAcionado = intent.getStringExtra("cbAcionado").toString()
        retornoViaturas = intent.getStringExtra("vtrEmpenhada").toString()
        retornoEfetivo = intent.getStringExtra("efetivo").toString()

        retornoVitIlsea = intent.getIntExtra("vitIlesa", 0)
        retornoVitCod1 = intent.getIntExtra("vitCod1", 0)
        retornoVitCod2 = intent.getIntExtra("vitCod2", 0)
        retornoVitCod3 = intent.getIntExtra("vitCod3",0)
        retornoVitCod4 = intent.getIntExtra("vitCod4", 0)
        retornoTotalVitimas = intent.getIntExtra("totalVit",0)
        retornoObsVitimas = intent.getStringExtra("obsservacaoVit").toString()

        retornoMeioAmbiente = intent?.extras?.getString("meioAmbiente")?.takeIf { it.isNotBlank() }
            ?: "Não houve | Não se aplica."
        retornoDanosPropriedade =
            intent?.extras?.getString("danosPropriedade")?.takeIf { it.isNotBlank() }
                ?: "Não houve | Não se aplica."
        retornoCenario =
            intent?.extras?.getString("cenario")?.takeIf { it.isNotBlank() } ?: "Não apurado."
        retornoDesdobramento =
            intent?.extras?.getString("desdobramento")?.takeIf { it.isNotBlank() }
                ?: "Não Informado."
        retornoApoio =
            intent?.extras?.getString("selecaoApoio")?.takeIf { it.isNotBlank() }
                ?: "Não houve."

        binding.txtCrbm.text = retornoCrbm
        binding.txtObm.text = retornoObm
        binding.txtNome.text = retornoGraduacaoNome
        binding.txtData.text = retornoData
        binding.txtHora.text = retronoHora
        binding.txtNatureza.text = retornoNatureza
        binding.txtSubNatureza.text = retornoSubNatureza
        binding.txtCidade.text = retornoCidade
        binding.txtLogradouro.text = retornoLogradouro
        binding.txtBairro.text = retornoBairro
        binding.txtComplemento.text = retornoComplemento
        binding.txtCbAcionado.text = retornoCbAcionado
        binding.vtrEmpenhadas.text = retornoViaturas
        binding.numBm.text = retornoEfetivo

        binding.txtIlesa.text = retornoVitIlsea.toString()
        binding.txtCod1.text = retornoVitCod1.toString()
        binding.txtCod2.text = retornoVitCod2.toString()
        binding.txtCod3.text = retornoVitCod3.toString()
        binding.txtCod4.text = retornoVitCod4.toString()
        binding.totalVitimas.text = retornoTotalVitimas.toString()
        binding.txtObsVitimas.text = retornoObsVitimas

        binding.txtMeioAmbiente.text = retornoMeioAmbiente
        binding.txtDanosPropriedade.text = retornoDanosPropriedade
        binding.txtCenario.text = retornoCenario
        binding.txtDesdobramento.text = retornoDesdobramento
        binding.txtApoio.text = retornoApoio

        binding.btnGerarPdf.setOnClickListener {

            comandoRegional = when (retornoCrbm) {
                "1º CRBM Curitiba" -> {
                    getString(R.string.crbm1)
                }

                "2º CRBM Londrina" -> {
                    getString(R.string.crbm2)
                }

                else -> {
                    getString(R.string.crbm3)
                }
            }
            gerarPdf()
            finish()
        }
    }
    private fun gerarPdf() {

        val pageWidth = 595
        val pageHheight = 842
        val fontSize = 9

        val pageInfo = PdfDocument.PageInfo.Builder(pageWidth, pageHheight, 1).create()
        val page = pdfDocument.startPage(pageInfo)
        val canvas = page.canvas

        val bitmap1 = BitmapFactory.decodeResource(resources, R.drawable.logo1)
        val bitmap2 = BitmapFactory.decodeResource(resources, R.drawable.logo2)

        val scaledLogoBitmap1 = Bitmap.createScaledBitmap(bitmap1, 140, 37, true)
        val scaledLogoBitmap2 = Bitmap.createScaledBitmap(bitmap2, 140, 37, true)

        canvas.drawBitmap(scaledLogoBitmap1, 35f, 40f, null)
        canvas.drawBitmap(scaledLogoBitmap2, 415f, 35f, null)

        val label1 = Paint()
        label1.textSize = fontSize.toFloat()
        label1.textAlign = Paint.Align.CENTER
        label1.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        canvas.drawText(comandoRegional, 300f, 90f, label1)
        canvas.drawText(retornoObm, 300f, 105f, label1)

        val label2 = Paint()
        label2.textSize = fontSize.toFloat()
        label2.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        label2.color = getColor(R.color.red)
        canvas.drawText("Para uso exclusivo no grupos CBMPR!", 40f, 125f, label2)

        canvas.drawText("Informações iniciais", 300f, 140f, label1)

        val label3 = Paint()
        label3.textSize = fontSize.toFloat()
        canvas.drawText("Responsável pela informação:", 40f, 155f, label3)
        canvas.drawText(retornoGraduacaoNome, 162f, 155f, label3)

        val label4 = Paint()
        label4.textSize = fontSize.toFloat()

        label1.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        canvas.drawText(comandoRegional, 300f, 90f, label1)
        canvas.drawText(retornoObm, 300f, 105f, label1)

        canvas.drawText("Data:", 40f, 170f, label3)
        canvas.drawText(retornoData, 64f, 170f, label3)

        canvas.drawText("Horas:", 40f, 185f, label3)
        canvas.drawText(retronoHora, 67f, 185f, label3)

        canvas.drawText("Natureza do evento:", 40f, 200f, label3)
        canvas.drawText(retornoNatureza, 123f, 200f, label3)

        canvas.drawText("Sub Natureza:", 40f, 215f, label3)
        canvas.drawText(retornoSubNatureza, 98f, 215f, label3)

        canvas.drawText("Endereço", 300f, 230f, label1)

        canvas.drawText("Cidade:", 40f, 245f, label3)
        canvas.drawText(retornoCidade, 72f, 245f, label3)

        canvas.drawText("Logradouro:", 40f, 260f, label3)
        canvas.drawText(retornoLogradouro, 90f, 260f, label3)

        canvas.drawText("Bairro:", 40f, 275f, label3)
        canvas.drawText(retornoBairro, 69f, 275f, label3)

        canvas.drawText("Complemento:", 40f, 290f, label3)
        canvas.drawText(retornoComplemento, 102f, 290f, label3)

        canvas.drawText("Recursos", 300f, 305f, label1)

        canvas.drawText("Unidade Acionada:", 40f, 320f, label3)
        canvas.drawText(retornoCbAcionado, 122f, 320f, label3)

        canvas.drawText("Efetivo, nº BMs:", 40f, 335f, label3)
        canvas.drawText(retornoEfetivo, 110f, 335f, label3)

        canvas.drawText("Viaturas Empenhadas:", 40f, 350f, label3)
        canvas.drawText(retornoViaturas, 135f, 350f, label3)

        canvas.drawText("Vítimas", 300f, 365f, label1)

        canvas.drawText("Total de vítimas:", 40f, 380f, label3)
        canvas.drawText(retornoTotalVitimas.toString(), 105f, 380f, label3)

        canvas.drawText("Vítima ilesa:", 40f, 395f, label3)
        canvas.drawText(retornoVitIlsea.toString(), 90f, 395f, label3)

        canvas.drawText("código 1:", 115f, 395f, label3)
        canvas.drawText(retornoVitCod1.toString(), 155f, 395f, label3)

        canvas.drawText("código 2:", 180f, 395f, label3)
        canvas.drawText(retornoVitCod2.toString(), 218f, 395f, label3)

        canvas.drawText("código 3:", 245f, 395f, label3)
        canvas.drawText(retornoVitCod3.toString(), 285f, 395f, label3)

        canvas.drawText("código 4:", 310f, 395f, label3)
        canvas.drawText(retornoVitCod4.toString(), 350f, 395f, label3)

        canvas.drawText("Observações das vítimas:", 40f, 416f, label4)

        val textPaint = TextPaint()
        textPaint.textSize = fontSize.toFloat()
        canvas.drawMultiLineText("$retornoObsVitimas", textPaint, 510, 40F, 420F, 0)

        canvas.drawText("Danos ao meio ambiente", 300f, 470f, label1)
        canvas.drawMultiLineText(retornoMeioAmbiente, textPaint, 510, 40F, 475F, 0)

        canvas.drawText("Danos à propriedade", 300f, 535f, label1)
        canvas.drawMultiLineText(retornoDanosPropriedade, textPaint, 510, 40F, 540F, 0)

        canvas.drawText("Cenário", 300f, 595f, label1)
        canvas.drawMultiLineText(retornoCenario, textPaint, 510, 40F, 600F, 0)

        canvas.drawText("Desdobramento", 300f, 655f, label1)
        canvas.drawMultiLineText(retornoDesdobramento, textPaint, 510, 40F, 660F, 0)

        canvas.drawText("Apoio", 300f, 780f, label1)
        canvas.drawMultiLineText(retornoApoio, textPaint, 510, 40F, 785F, 0)

        pdfDocument.finishPage(page)
        salvarPdf()
    }

    private fun salvarPdf() {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy HH-mm", Locale.getDefault())
        val fileName = "${dateFormat.format(System.currentTimeMillis())} IPO.pdf"

        val resolver = contentResolver
        val contentValues = contentValuesOf(
            MediaStore.MediaColumns.DISPLAY_NAME to fileName,
            MediaStore.MediaColumns.MIME_TYPE to "application/pdf",
            MediaStore.MediaColumns.RELATIVE_PATH to "Documents/"
        )

        val uri = resolver.insert(MediaStore.Files.getContentUri("external"), contentValues)

        uri?.let {
            try {
                val outputStream: OutputStream? = resolver.openOutputStream(it)
                pdfDocument.writeTo(outputStream)
                outputStream?.close()
                Toast.makeText(this, "Arquivo salvo na pasta de documentos.", Toast.LENGTH_LONG)
                    .show()

                //Abrir o arquivo salvo
                val intent = Intent(Intent.ACTION_VIEW)
                intent.setDataAndType(it, "application/pdf")
                intent.flags =
                    Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_GRANT_READ_URI_PERMISSION
                startActivity(intent)

            } catch (e: IOException) {
                Toast.makeText(this, "Erro ao salvar o arquivo.", Toast.LENGTH_LONG).show()
            }
        }
        pdfDocument.close()
    }
    private fun Canvas.drawMultiLineText(
        texto: String,
        textPaint: TextPaint,
        largura: Int,
        x: Float,
        y: Float,
        inicio: Int = 0,
        fim: Int = texto.length,
        alinhamento: Layout.Alignment = Layout.Alignment.ALIGN_NORMAL,
        justificaMode: Int = LineBreaker.JUSTIFICATION_MODE_INTER_WORD
    ) {
        val cacheKey = "$texto-$inicio-$fim-$textPaint-$largura-$alinhamento-$justificaMode"
        val staticLayout = StaticLayoutCache[cacheKey] ?: StaticLayout.Builder.obtain(
            texto,
            inicio,
            fim,
            textPaint,
            largura
        )
            .setAlignment(alinhamento)
            .setJustificationMode(justificaMode)
            .build().apply { StaticLayoutCache[cacheKey] = this }
        staticLayout.draw(this, x, y)
    }
}



private fun StaticLayout.draw(canvas: Canvas, x: Float, y: Float) {
    canvas.withTranslation(x, y) {
        draw(this)
    }
}

private object StaticLayoutCache {
    private const val MAX_SIZE = 50 // Arbitrary max number of cached items

    private val cache = lruCache<String, StaticLayout>(MAX_SIZE)
    operator fun set(key: String, staticLayout: StaticLayout) {
        cache.put(key, staticLayout)
    }

    operator fun get(key: String): StaticLayout? {
        return cache[key]
    }
}
