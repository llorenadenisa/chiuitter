package ro.upt.ac.chiuitter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_chiuit.*
import kotlinx.android.synthetic.main.view_home.*
import android.util.Log


class HomeActivity : AppCompatActivity() {

    private val dummyChiuitStore = DummyChiuitStore()

    private lateinit var listAdapter: ChiuitRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_home)
        fab_add.setOnClickListener { composeChiuit() }
        initList()
    }

    private fun initList() {
        val chiuitList = dummyChiuitStore.getAllData()
        //TODO("7. Instantiate the adapter, then setup the recycler view list")
        listAdapter = ChiuitRecyclerViewAdapter(chiuitList as ArrayList<Chiuit>, fun (chiuit : Chiuit):Unit = shareChiuit(chiuit.description)  )
        val recyclerView = findViewById<RecyclerView>(R.id.rv_chiuit_list).apply {
            setHasFixedSize(true)
            adapter = listAdapter
        }
        recyclerView.layoutManager = LinearLayoutManager(this)

    }


    /*
    Defines text sharing/sending *implicit* intent, opens the application chooser menu,
    and starts a new activity which supports sharing/sending text.
     */
    private fun shareChiuit(text: String) {
        val sendIntent = Intent().apply {
            // TODO 1: Configure to support text sending/sharing and then attach the text as intent's extra.
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text.toString())
            type = "text/plain"

        }

        val intentChooser = Intent.createChooser(sendIntent, "")

        startActivity(intentChooser)
    }

    /*
    Defines an *explicit* intent which will be used to start ComposeActivity.
     */
    private fun composeChiuit() {
        // TODO 2: Create an explicit intent which points to ComposeActivity.
        val composeActivityIntent = Intent(this, ComposeActivity::class.java)

        // TODO 3: Start a new activity with the previously defined intent.
        // We start a new activity that we expect to return the acquired text as the result.
        startActivityForResult(composeActivityIntent, COMPOSE_REQUEST_CODE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d("Param", requestCode.toString() + resultCode + data.toString() )
        when (requestCode) {
            COMPOSE_REQUEST_CODE -> if (resultCode == Activity.RESULT_OK) extractText(data)
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun extractText(data: Intent?) {
        data?.let {
            // TODO 5: Extract the text from result intent.
            val extraText = data.getStringExtra("extra_text")

            // TODO 6: Check if text is not null or empty, then set the new "chiuit" content.
            if (!extraText.isEmpty()) {
               // txv_content.setText(extraText)
                //TODO("13. Instantiate a new chiuit object that add it to the adapter")
                val newChiuit = Chiuit(extraText)
                listAdapter.addItem(newChiuit)
            }
        }
    }

    companion object {
        const val COMPOSE_REQUEST_CODE = 1213
    }

}
