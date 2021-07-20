package com.example.idletravel

import android.graphics.Point
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.idletravel.area.Area
import com.example.idletravel.area.maps.grasslandArea
import com.example.idletravel.customItem.CustomItem
import com.example.idletravel.customItem.ItemMap
import com.example.idletravel.format.formatButton
import com.example.idletravel.format.formatTextView
import com.example.idletravel.format.formatView
import com.example.idletravel.itemView.ItemView
import com.example.idletravel.player.Player
import com.example.idletravel.travel.Travel
import com.example.idletravel.widgetsList.WidgetsList
import kotlinx.android.synthetic.main.activity_start_game.*
import java.text.DateFormat
import java.util.*


class StartGame : AppCompatActivity() {
    private val travel = Travel(this)

    private val mainViewOptionList: List<String> = listOf("日志", "物品", "地图", "队列", "人物", "系统")

    // 主视图选单
    private var mainViewOptionCurrent: String = mainViewOptionList[0]
    // 主视图当前选择的视图

    private val travelLogWidgetsList = WidgetsList()
    private val itemWidgetsList = WidgetsList()
    private val mapWidgetsList = WidgetsList()
    private val travelListWidgetsList = WidgetsList()
    private val playerWidgetsList = WidgetsList()
    private val systemWidgetsList = WidgetsList()

    // 每个视图对应一个组件列表

    private val mainViewWidgetsListMap: Map<String, WidgetsList> = mapOf(
        mainViewOptionList[0] to travelLogWidgetsList,
        mainViewOptionList[1] to itemWidgetsList,
        mainViewOptionList[2] to mapWidgetsList,
        mainViewOptionList[3] to travelListWidgetsList,
        mainViewOptionList[4] to playerWidgetsList,
        mainViewOptionList[5] to systemWidgetsList
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_game)

        player = this@StartGame.intent.getSerializableExtra("player") as Player
        val bundle = this@StartGame.intent.extras
        if (bundle != null) {
            val map = bundle.get("item") as ItemMap
            itemMap = map.map
        }

        setStartGameButtonOnClickListener()

        mapFillMainView()
        itemFillMainView()
    }

    private fun setStartGameButtonOnClickListener() {
        startGameTravelLogButton.setOnClickListener {
            val mainViewOptionListIndex = 0
            this.menuButtonOnClick(mainViewOptionListIndex)
        }
        startGameItemButton.setOnClickListener {
            val mainViewOptionListIndex = 1
            this.menuButtonOnClick(mainViewOptionListIndex)
        }
        startGameMapButton.setOnClickListener {
            val mainViewOptionListIndex = 2
            this.menuButtonOnClick(mainViewOptionListIndex)
        }
        startGameTravelListButton.setOnClickListener {
            val mainViewOptionListIndex = 3
            this.menuButtonOnClick(mainViewOptionListIndex)
        }
        startGamePlayerButton.setOnClickListener {
            val mainViewOptionListIndex = 4
            this.menuButtonOnClick(mainViewOptionListIndex)
        }
        startGameSystemButton.setOnClickListener {
            val mainViewOptionListIndex = 5
            this.menuButtonOnClick(mainViewOptionListIndex)
        }
    }

    private fun menuButtonOnClick(mainViewOptionListIndex: Int) {
        // 选单按钮点击事件
        val option = mainViewOptionList[mainViewOptionListIndex]
        // option 获取一个字符串用于和当前选择视图字符串比对
        if (mainViewOptionCurrent != option) {
            mainViewWidgetsListMap[option]?.changeMainViewContent(startGameMainLayout)
            mainViewOptionCurrent = option
        }
    }

    private fun mapFillMainView() {
        // TODO: 待完善
        createMapView(grasslandArea)
        createMapView(grasslandArea)
        createMapView(grasslandArea)
        createMapView(grasslandArea)
        createMapView(grasslandArea)
        createMapView(grasslandArea)
        createMapView(grasslandArea)
        createMapView(grasslandArea)
        createMapView(grasslandArea)
        createMapView(grasslandArea)
        createMapView(grasslandArea)
        createMapView(grasslandArea)
        createMapView(grasslandArea)
        createMapView(grasslandArea)
    }

    private fun createMapView(area: Area) {
        // 地图选单特有创建
        val defaultDisplay = windowManager.defaultDisplay
        val point = Point()
        defaultDisplay.getSize(point)
        val width = point.x
        val height = point.y / 2
        //  TODO: 高度应该是动态的

        val mapButton = formatButton(
            Button(this@StartGame),
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ),
            text = area.name,
            textSize = 30F
        )
        if (this.mainViewOptionCurrent == "地图") {
            mapButton.visibility = View.VISIBLE
        }

        val frameParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(
                width,
                height
            )
        frameParams.gravity = Gravity.CENTER
        val frameLayout = formatView(
            FrameLayout(this@StartGame),
            frameParams
        ) as FrameLayout

        val linearLayout = formatView(
            LinearLayout(this@StartGame),
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        ) as LinearLayout
        linearLayout.orientation = LinearLayout.VERTICAL


        val nameView = formatTextView(
            TextView(this@StartGame),
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                0,
                2F
            ),
            text = area.name,
            textSize = 50F
        )
        nameView.gravity = Gravity.CENTER

        val informationView = formatTextView(
            TextView(this@StartGame),
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                0,
                4F
            ),
            text = area.information,
            textSize = 30F
        )
        informationView.gravity = ViewGroup.TEXT_ALIGNMENT_CENTER

        val buttonLayout = formatView(
            LinearLayout(this@StartGame),
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                0,
                1F
            )
        ) as LinearLayout
        buttonLayout.orientation = LinearLayout.HORIZONTAL

        val finishButton = formatButton(
            Button(this@StartGame),
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ),
            text = "确定",
            textSize = 30F
        )

        val closeButton = formatButton(
            Button(this@StartGame),
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ),
            text = "关闭",
            textSize = 30F
        )

        mapButton.setOnClickListener {
            val visible = inverseVisibility(frameLayout)
            frameLayout.visibility = visible
            linearLayout.visibility = visible
            nameView.visibility = visible
            informationView.visibility = visible
            buttonLayout.visibility = visible
            finishButton.visibility = visible
            closeButton.visibility = visible
        }

        closeButton.setOnClickListener {
            frameLayout.visibility = View.GONE
            linearLayout.visibility = View.GONE
            nameView.visibility = View.GONE
            informationView.visibility = View.GONE
            buttonLayout.visibility = View.GONE
            finishButton.visibility = View.GONE
            closeButton.visibility = View.GONE
        }

        finishButton.setOnClickListener {
            // 这是旅行事件入口
            travel.addTravelPlan(area)
        }

        buttonLayout.addView(finishButton)
        buttonLayout.addView(closeButton)

        linearLayout.addView(nameView)
        linearLayout.addView(informationView)
        linearLayout.addView(buttonLayout)

        frameLayout.addView(linearLayout)

        startGameMainLayout.addView(mapButton)
        startGameMainLayout.addView(frameLayout)

        // 注意这里
        mapWidgetsList.widgetsList.add(mapButton)
    }

    private fun itemFillMainView() {
        for ((key, value) in itemMap) {
            createItemView(key, value)
        }
    }

    fun createItemView(key: CustomItem, value: Int) {
        val linearLayout = formatView(
            LinearLayout(this@StartGame),
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ),
        ) as LinearLayout
        linearLayout.orientation = LinearLayout.HORIZONTAL

        val itemTextView = formatTextView(
            TextView(this@StartGame),
            LinearLayout.LayoutParams(
                0,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1F
            ),
            text = key.name,
            textSize = 30F
        )

        val itemCountTextView = formatTextView(
            TextView(this@StartGame),
            LinearLayout.LayoutParams(
                0,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1F
            ),
            text = value.toString(),
            textSize = 30F
        )
        itemCountTextView.gravity = Gravity.END

        linearLayout.addView(itemTextView)
        linearLayout.addView(itemCountTextView)

        itemWidgetsList.widgetsList.add(linearLayout)
        itemWidgetsList.widgetsList.add(itemTextView)
        itemWidgetsList.widgetsList.add(itemCountTextView)

        itemViewMap[key] = ItemView(linearLayout, itemTextView, itemCountTextView)

        startGameMainLayout.addView(linearLayout)
    }

    fun createTravelListView(area: Area){
        val travelButton = formatButton(
            Button(this@StartGame),
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ),
            text = area.name,
            textSize = 30F
        )
        // TODO:取消队列相关
    }

    fun createTravelLogView(message: String){
        val dateFormat: DateFormat = DateFormat.getTimeInstance(DateFormat.MEDIUM, Locale.CHINA)
        val time:String = dateFormat.format(Date())


        val travelTextView = formatTextView(
            TextView(this@StartGame),
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ),
            text = "[$time]: $message",
            textSize = 30F
        )

        startGameMainLayout.addView(travelTextView)

        travelLogWidgetsList.widgetsList.add(travelTextView)
    }
}

fun inverseVisibility(view: View): Int {
    return if (view.visibility == View.VISIBLE) {
        View.GONE
    } else {
        View.VISIBLE
    }
}

// 这玩意写外面因为得获取travel结果
var itemMap: MutableMap<CustomItem, Int> = mutableMapOf()

// 用于更新视图
// list中, 0为布局, 1为物品名称TextView, 2为物品数量TextView
var itemViewMap: MutableMap<CustomItem, ItemView> = mutableMapOf()


var player: Player = Player("debug", "debug", 65535)
