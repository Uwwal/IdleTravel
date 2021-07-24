package com.example.idletravel

import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.idletravel.area.CustomArea
import com.example.idletravel.customItem.CustomItem
import com.example.idletravel.databinding.ActivityStartGameBinding
import com.example.idletravel.format.*
import com.example.idletravel.gameCalender.GameCalendar
import com.example.idletravel.player.Player
import com.example.idletravel.transmissionMap.TransmissionMap
import com.example.idletravel.travel.Travel
import com.example.idletravel.travelListButton.TravelListButton
import com.example.idletravel.widgetsList.WidgetsList
import java.text.DateFormat
import java.util.*
import kotlin.collections.ArrayList

class StartGameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartGameBinding

    private val travel = Travel(this)

    private val mainViewOptionList: List<String> = listOf(
        "日志", "物品", "地图", "队列", "人物", "事件", "合成", "日历", "系统"
    )
    // 主视图选单
    // 增加选单要修改mainViewOptionList, 添加WidgetsList, 设置点击监听器, mainViewWidgetsListMap, 视图也要添加组件.

    private var mainViewOptionCurrent: String = mainViewOptionList[0]
    // 主视图当前选择的视图

    private val travelLogWidgetsList = WidgetsList()
    private val itemWidgetsList = WidgetsList()
    private val mapWidgetsList = WidgetsList()
    private val travelListWidgetsList = WidgetsList()
    private val playerWidgetsList = WidgetsList()
    private val eventWidgetsList = WidgetsList()
    private val productionWidgetsList = WidgetsList()
    private val calendarWidgetsList = WidgetsList()
    private val systemWidgetsList = WidgetsList()

    // 每个视图对应一个组件

    private val playerViewList: MutableList<TextView> = ArrayList()

    private val mainViewWidgetsListMap: Map<String, WidgetsList> = mapOf(
        mainViewOptionList[0] to travelLogWidgetsList,
        mainViewOptionList[1] to itemWidgetsList,
        mainViewOptionList[2] to mapWidgetsList,
        mainViewOptionList[3] to travelListWidgetsList,
        mainViewOptionList[4] to playerWidgetsList,
        mainViewOptionList[5] to eventWidgetsList,
        mainViewOptionList[6] to productionWidgetsList,
        mainViewOptionList[7] to calendarWidgetsList,
        mainViewOptionList[8] to systemWidgetsList,
    )

    // 反序列化生成的对象与原对象并不相同
    var itemCountMap: MutableMap<String, Int> = mutableMapOf()

    var itemCountTextViewMap: MutableMap<String, TextView> = mutableMapOf()

    var travelListButtonList: MutableList<TravelListButton> = ArrayList()

    private val layoutParamsFullWidth = LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )

    private lateinit var calendarTextView: TextView
    private lateinit var seasonInformationTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartGameBinding.inflate(LayoutInflater.from(this@StartGameActivity))
        setContentView(binding.root)

        setVariablesWithIntent()

        setStartGameButtonOnClickListener()

        // 这些xxFillMainView只在初始化调用一次
        mapFillMainView()
        itemFillMainView()
        playerFillMainView()
        calendarFillMainView()
    }

    private fun calendarFillMainView() {
        // 创建calendar视图特有组件
        this@StartGameActivity.runOnUiThread {
            calendarTextView = formatTextView(
                TextView(this@StartGameActivity),
                layoutParamsFullWidth,
                text = formatGameCalendarToTime(gameCalendar),
                textSize = 30F
            )

            seasonInformationTextView = formatTextView(
                TextView(this@StartGameActivity),
                layoutParamsFullWidth,
                text = gameCalendar.season.information,
                textSize = 15F
            )

            calendarWidgetsList.widgetsList.add(calendarTextView)
            calendarWidgetsList.widgetsList.add(seasonInformationTextView)

            binding.startGameMainLayout.addView(calendarTextView)
            binding.startGameMainLayout.addView(seasonInformationTextView)
        }
    }

    fun updateCalendarTextView(){
        calendarTextView.text = formatGameCalendarToTime(gameCalendar)
        seasonInformationTextView.text = gameCalendar.season.information

        createTravelLogView("${gameCalendar.season.seasonName}悄然来到..")
    }

    private fun setVariablesWithIntent() {
        // 从CreatePlayerActivity和SelectSaveActivity获取Intent
        val bundle = this@StartGameActivity.intent.extras
        bundle?.let {
            val map = bundle.get("item") as TransmissionMap
            itemCountMap = map.map

            player = bundle.get("player") as Player

            gameCalendar = bundle.get("gameCalendar") as GameCalendar
        }
    }

    private fun setStartGameButtonOnClickListener() {
        // 目的是设置mainViewOptionListIndex
        binding.startGameTravelLogButton.setOnClickListener {
            val mainViewOptionListIndex = 0
            this.menuButtonOnClick(mainViewOptionListIndex)
        }
        binding.startGameItemButton.setOnClickListener {
            val mainViewOptionListIndex = 1
            this.menuButtonOnClick(mainViewOptionListIndex)
        }
        binding.startGameMapButton.setOnClickListener {
            val mainViewOptionListIndex = 2
            this.menuButtonOnClick(mainViewOptionListIndex)
        }
        binding.startGameTravelListButton.setOnClickListener {
            val mainViewOptionListIndex = 3
            this.menuButtonOnClick(mainViewOptionListIndex)
        }
        binding.startGamePlayerButton.setOnClickListener {
            val mainViewOptionListIndex = 4
            this.menuButtonOnClick(mainViewOptionListIndex)
        }
        binding.startGameEventButton.setOnClickListener {
            val mainViewOptionListIndex = 5
            this.menuButtonOnClick(mainViewOptionListIndex)
        }
        binding.startGameProductionButton.setOnClickListener {
            val mainViewOptionListIndex = 6
            this.menuButtonOnClick(mainViewOptionListIndex)
        }
        binding.startGameCalenderButton.setOnClickListener {
            val mainViewOptionListIndex = 7
            this.menuButtonOnClick(mainViewOptionListIndex)
        }
        binding.startGameSystemButton.setOnClickListener {
            val mainViewOptionListIndex = 8
            this.menuButtonOnClick(mainViewOptionListIndex)
        }
    }

    private fun menuButtonOnClick(mainViewOptionListIndex: Int) {
        // 选单按钮点击事件
        val option = mainViewOptionList[mainViewOptionListIndex]
        // option 获取一个字符串用于和当前选择视图字符串比对
        if (mainViewOptionCurrent != option) {
            mainViewWidgetsListMap[option]?.changeMainViewContent(binding.startGameMainLayout)
            mainViewOptionCurrent = option
        }
    }

    private fun playerFillMainView() {
        // 创建player视图组件
        this@StartGameActivity.runOnUiThread {
            val visibility: Int = getVisibilityWithMainViewOptionCurrent("人物")

            val playerNameTextView = formatTextView(
                TextView(this@StartGameActivity),
                layoutParamsFullWidth,
                visibility,
                text = "名字:   " + player.name,
                textSize = 30F
            )

            val playerSexTextView = formatTextView(
                TextView(this@StartGameActivity),
                layoutParamsFullWidth,
                visibility,
                text = "性别:   " + player.sex,
                textSize = 30F
            )

            val playerStatusTextView = formatTextView(
                TextView(this@StartGameActivity),
                layoutParamsFullWidth,
                visibility,
                text = formatPlayerStatusTextEightLines(player.status),
                textSize = 30F
            )

            playerWidgetsList.widgetsList.add(playerNameTextView)
            playerWidgetsList.widgetsList.add(playerSexTextView)
            playerWidgetsList.widgetsList.add(playerStatusTextView)

            playerViewList.add(playerNameTextView)
            playerViewList.add(playerSexTextView)
            playerViewList.add(playerStatusTextView)

            binding.startGameMainLayout.addView(playerNameTextView)
            binding.startGameMainLayout.addView(playerSexTextView)
            binding.startGameMainLayout.addView(playerStatusTextView)
        }
    }

    fun updatePlayerStatusTextView() {
        // player视图属性更新
        // travel类有属性奖励
        playerViewList[2].text = formatPlayerStatusTextEightLines(player.status)
    }

    private fun mapFillMainView() {
        // 添加的时候记得去player完善finishTime
        createMapView(CustomArea.LAWN)
    }

    private fun createMapView(area: CustomArea) {
        // 地图选单特有创建
        this@StartGameActivity.runOnUiThread {
            val mapButton = formatButton(
                Button(this@StartGameActivity),
                layoutParamsFullWidth,
                text = area.areaName,
                textSize = 30F
            )
            if (this.mainViewOptionCurrent == "地图") {
                mapButton.visibility = View.VISIBLE
            }

            val baseLayout = formatView(
                LinearLayout(this@StartGameActivity),
                layoutParamsFullWidth,
            ) as LinearLayout
            baseLayout.orientation = LinearLayout.VERTICAL

            val linearLayout = formatView(
                LinearLayout(this@StartGameActivity),
                LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            ) as LinearLayout
            linearLayout.orientation = LinearLayout.VERTICAL


            val informationView = formatTextView(
                TextView(this@StartGameActivity),
                layoutParamsFullWidth,
                text = area.information,
                textSize = 15F
            )
            informationView.gravity = ViewGroup.TEXT_ALIGNMENT_CENTER

            val buttonLayout = formatView(
                LinearLayout(this@StartGameActivity),
                layoutParamsFullWidth,
            ) as LinearLayout
            buttonLayout.orientation = LinearLayout.HORIZONTAL

            val finishButton = formatButton(
                Button(this@StartGameActivity),
                LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ),
                text = "确定",
                textSize = 30F
            )

            val closeButton = formatButton(
                Button(this@StartGameActivity),
                LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ),
                text = "关闭",
                textSize = 30F
            )

            mapButton.setOnClickListener {
                val visible = inverseVisibility(baseLayout)
                baseLayout.visibility = visible
                linearLayout.visibility = visible
                informationView.visibility = visible
                buttonLayout.visibility = visible
                finishButton.visibility = visible
                closeButton.visibility = visible
            }

            closeButton.setOnClickListener {
                baseLayout.visibility = View.GONE
                linearLayout.visibility = View.GONE
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

            linearLayout.addView(informationView)
            linearLayout.addView(buttonLayout)

            baseLayout.addView(linearLayout)

            binding.startGameMainLayout.addView(mapButton)
            binding.startGameMainLayout.addView(baseLayout)

            // 注意这里
            // 因为这个widgetList添加后点击自动Visible, 而baseLayout等组件默认收缩, 所以不要添加进去
            mapWidgetsList.widgetsList.add(mapButton)
        }
    }

    private fun itemFillMainView() {
        // 遍历物品创建对应物品
        for ((key, value) in itemCountMap) {
            createItemView(CustomItem.valueOf(key), value)
        }
    }

    fun createItemView(key: CustomItem, value: Int) {
        // 这个函数暴露用于在travel更新物品
        this@StartGameActivity.runOnUiThread {
            val visibility: Int = getVisibilityWithMainViewOptionCurrent("物品")

            val linearLayout = formatView(
                LinearLayout(this@StartGameActivity),
                layoutParamsFullWidth,
                visibility
            ) as LinearLayout
            linearLayout.orientation = LinearLayout.HORIZONTAL

            val itemTextView = formatTextView(
                TextView(this@StartGameActivity),
                LinearLayout.LayoutParams(
                    0,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    1F
                ),
                visibility,
                text = key.itemName,
                textSize = 30F
            )

            val itemCountTextView = formatTextView(
                TextView(this@StartGameActivity),
                LinearLayout.LayoutParams(
                    0,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    1F
                ),
                visibility,
                text = value.toString(),
                textSize = 30F
            )

            val baseLayout = formatView(
                LinearLayout(this@StartGameActivity),
                layoutParamsFullWidth,
            ) as LinearLayout
            baseLayout.orientation = LinearLayout.VERTICAL

            val itemInformationTextView = formatTextView(
                TextView(this@StartGameActivity),
                layoutParamsFullWidth,
                visibility,
                text = key.information,
                textSize = 15F
            )

            itemTextView.setOnClickListener {
                val temVisibility: Int = inverseVisibility(baseLayout)
                baseLayout.visibility = temVisibility
                itemInformationTextView.visibility = temVisibility
            }

            itemCountTextView.gravity = Gravity.END

            // 添加之前判断
            val atBottom = judgeScrollToBottom() && mainViewOptionCurrent == "物品"

            linearLayout.addView(itemTextView)
            linearLayout.addView(itemCountTextView)

            itemWidgetsList.widgetsList.add(linearLayout)
            itemWidgetsList.widgetsList.add(itemTextView)
            itemWidgetsList.widgetsList.add(itemCountTextView)

            itemCountTextViewMap[key.name] = itemCountTextView

            baseLayout.addView(itemInformationTextView)

            binding.startGameMainLayout.addView(linearLayout)
            binding.startGameMainLayout.addView(baseLayout)

            if (atBottom){
                scrollViewToBottom()
            }
        }
    }

    fun createTravelListView(area: CustomArea) {
        // 队列不打算读取, 因此不需要fillMainView
        this@StartGameActivity.runOnUiThread {
            val visibility: Int = getVisibilityWithMainViewOptionCurrent("队列")

            val travelButton = formatButton(
                TravelListButton(this@StartGameActivity),
                layoutParamsFullWidth,
                visibility,
                text = area.areaName,
                textSize = 30F
            ) as TravelListButton

            travelButton.setOnClickListener {
                val index = travelButton.index

                if (index != 0) {
                    // 不能取消正在旅行的计划

                    val areaName = travelButton.text
                    binding.startGameMainLayout.removeView(travelButton)

                    createTravelLogView("${player.name}已经把${areaName}从计划里移除了!")

                    removeTravelListButton(travelButton)
                }

            }

            travelButton.index = travelListButtonList.size


            travelListButtonList.add(travelButton)

            travelListWidgetsList.widgetsList.add(travelButton)
            binding.startGameMainLayout.addView(travelButton)
        }
    }

    private fun getVisibilityWithMainViewOptionCurrent(targetOption: String) =
        // 当前视图与目标视图选项相同则显示
        if (mainViewOptionCurrent == targetOption) {
            View.VISIBLE
        } else {
            View.GONE
        }

    fun createTravelLogView(message: String) {
        // 中国时区
        // 后续添加日志用
        this@StartGameActivity.runOnUiThread {
            val dateFormat: DateFormat = DateFormat.getTimeInstance(DateFormat.MEDIUM, Locale.CHINA)
            val time: String = dateFormat.format(Date())

            val visibility: Int = getVisibilityWithMainViewOptionCurrent("日志")

            val travelTextView = formatTextView(
                TextView(this@StartGameActivity),
                layoutParamsFullWidth,
                visibility,
                text = "[$time]: $message",
                textSize = 15F
            )

            // 添加之前判断
            val atBottom = judgeScrollToBottom() && mainViewOptionCurrent == "日志"

            binding.startGameMainLayout.addView(travelTextView)

            travelLogWidgetsList.widgetsList.add(travelTextView)

            if (atBottom){
                scrollViewToBottom()
            }
        }
    }

    fun removeTravelListButton(travelButton: TravelListButton) {
        // 移除travelListButton
        binding.startGameMainLayout.removeView(travelButton)
        adjustTravelListButtonListIndex(travelButton.index)
    }

    private fun adjustTravelListButtonListIndex(index: Int) {
        // 调整index, 让移除后面的都index-1
        travel.travelList.removeAt(index)

        travelListButtonList.removeAt(index)
        travelListWidgetsList.widgetsList.removeAt(index)

        for (i in index until travelListButtonList.size) {
            travelListButtonList[i].index--
        }
    }

    private fun inverseVisibility(view: View): Int {
        // 取反Visibility
        return if (view.visibility == View.VISIBLE) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    private fun judgeScrollToBottom(): Boolean{
        // 返回是否滑到最底部
        val scrollView:ScrollView =binding.startGameScrollView
        return scrollView.scrollY + scrollView.height >= binding.startGameMainLayout.measuredHeight
    }

    private fun scrollViewToBottom(){
        val handler = Handler()
        val scrollView:ScrollView =binding.startGameScrollView

        handler.postDelayed({
            scrollView.smoothScrollTo(
                0,
                binding.startGameMainLayout.height
            )
        }, 350)
    }
}

// 这玩意写这里可以被import, 当全局变量用了, 主要Area类上下文传不进去, 只能这么用.
var player: Player = Player("debug", "debug")
var gameCalendar: GameCalendar = GameCalendar()