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
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.collection.lruCache
import androidx.core.content.contentValuesOf
import androidx.core.graphics.withTranslation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projetoipo.databinding.ActivityResumoBinding
import com.example.projetoipo.model.ItensResumo
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

    private var retornoVitIlsea = ""
    private var retornoVitCod1 = ""
    private var retornoVitCod2 = ""
    private var retornoVitCod3 = ""
    private var retornoVitCod4 = ""
    private var retornoTotalVitimas = ""
    private var retornoObsVitimas = ""

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

        retornoVitIlsea = intent.getIntExtra("vitIlesa", 0).toString()
        retornoVitCod1 = intent.getIntExtra("vitCod1", 0).toString()
        retornoVitCod2 = intent.getIntExtra("vitCod2", 0).toString()
        retornoVitCod3 = intent.getIntExtra("vitCod3", 0).toString()
        retornoVitCod4 = intent.getIntExtra("vitCod4", 0).toString()
        retornoTotalVitimas = intent.getIntExtra("totalVit", 0).toString()
        retornoObsVitimas = intent.getStringExtra("observacaoVit").toString()

        val itensResumo = ItensResumo(
            retornoCrbm,
            retornoObm,
            retornoGraduacaoNome,
            retornoData,
            retronoHora,
            retornoNatureza,
            retornoSubNatureza,
            retornoCidade,
            retornoLogradouro,
            retornoBairro,
            retornoComplemento,
            retornoCbAcionado,
            retornoViaturas,
            retornoEfetivo,
            retornoVitIlsea,
            retornoVitCod1,
            retornoVitCod2,
            retornoVitCod3,
            retornoVitCod4,
            retornoTotalVitimas,
            retornoObsVitimas
        )

        val adapter = ResumoAdapter(itensResumo)
        binding.rvResumo.adapter = adapter
        binding.rvResumo.layoutManager = LinearLayoutManager(this)

        Toast.makeText(this,"$retornoCbAcionado",Toast.LENGTH_LONG).show()

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

    //Classe que administra a recyclerview e suas células(os seus layouts)
    private inner class ResumoAdapter(private val itensResumo: ItensResumo) :
        RecyclerView.Adapter<ResumoAdapter.ResumoViewHolder>() {
        //Qual é o layout da célula(item)
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResumoViewHolder {
            val view = layoutInflater.inflate(R.layout.item_resumo, parent, false)
            val viewHolder = ResumoViewHolder(view)
            return viewHolder
        }

        //Quantas celulas irão aparecer na rolagem
        override fun getItemCount(): Int {
            return 1
        }

        //Responsável por informar quantas celulas essa listagem terá
        override fun onBindViewHolder(holder: ResumoViewHolder, position: Int) {
            val itemCurrent = itensResumo
            holder.bind(itemCurrent)
        }

        //Classe da célula
        private inner class ResumoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(item: ItensResumo) {
                val txtCrbm: TextView = itemView.findViewById(R.id.txt_crbm)
                val txtObm: TextView = itemView.findViewById(R.id.txt_obm)
                val txtNome: TextView = itemView.findViewById(R.id.txt_nome)
                val txtData: TextView = itemView.findViewById(R.id.txt_data)
                val txtHora: TextView = itemView.findViewById(R.id.txt_hora)
                val txtNatureza: TextView = itemView.findViewById(R.id.txt_natureza)
                val txtSubNatureza: TextView = itemView.findViewById(R.id.txt_subNatureza)
                val txtCidade: TextView = itemView.findViewById(R.id.txt_cidade)
                val txtLogradouro: TextView = itemView.findViewById(R.id.txt_logradouro)
                val txtBairro: TextView = itemView.findViewById(R.id.txt_bairro)
                val txtComplemento: TextView = itemView.findViewById(R.id.txt_complemento)
                val txtCbAcionado: TextView = itemView.findViewById(R.id.txt_cbAcionado)
                val txtEfetivo: TextView = itemView.findViewById(R.id.num_bm)
                val txtViaturas: TextView = itemView.findViewById(R.id.vtrEmpenhadas)

                val txtVitIlsea: TextView = itemView.findViewById(R.id.txtIlesa)
                val txtVitCod1: TextView = itemView.findViewById(R.id.txtCod1)
                val txtVitCod2: TextView = itemView.findViewById(R.id.txtCod2)
                val txtVitCod3: TextView = itemView.findViewById(R.id.txtCod3)
                val txtVitCod4: TextView = itemView.findViewById(R.id.txtCod4)
                val txtTtotalVitimas: TextView = itemView.findViewById(R.id.totalVitimas)
                val txtObsVitimas: TextView = itemView.findViewById(R.id.txtObsVitimas)

                txtCrbm.text = item.crbm
                txtObm.text = item.obm
                txtNome.text = item.nome
                txtData.text = item.data
                txtHora.text = item.hora
                txtNatureza.text = item.natureza
                txtSubNatureza.text = item.subNatureza
                txtCidade.text = item.cidade
                txtLogradouro.text = item.logradouro
                txtBairro.text = item.bairro
                txtComplemento.text = item.complemento
                txtCbAcionado.text = item.cbAcionado
                txtEfetivo.text = item.efetivo
                txtViaturas.text = item.viaturas

                txtVitIlsea.text = item.vitIlesa
                txtVitCod1.text = item.vitCod1
                txtVitCod2.text = item.vitCod2
                txtVitCod3.text = item.vitCod3
                txtVitCod4.text = item.vitCod4
                txtTtotalVitimas.text = item.totalVitimas
                txtObsVitimas.text = item.obsVitimas
            }
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
        canvas.drawText(retornoTotalVitimas, 105f, 380f, label3)

        canvas.drawText("Vítima ilesa:", 40f, 395f, label3)
        canvas.drawText(retornoVitIlsea, 90f, 395f, label3)

        canvas.drawText("código 1:", 115f, 395f, label3)
        canvas.drawText(retornoVitCod1, 155f, 395f, label3)

        canvas.drawText("código 2:", 180f, 395f, label3)
        canvas.drawText(retornoVitCod2, 218f, 395f, label3)

        canvas.drawText("código 3:", 245f, 395f, label3)
        canvas.drawText(retornoVitCod3, 285f, 395f, label3)

        canvas.drawText("código 4:", 310f, 395f, label3)
        canvas.drawText(retornoVitCod4, 350f, 395f, label3)

        canvas.drawText("Observações das vítimas:", 40f, 416f, label4)

        val textPaint = TextPaint()
        textPaint.textSize = fontSize.toFloat()

        canvas.drawMultiLineText("$retornoObsVitimas", textPaint, 510, 40F, 425F, 0)

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
