package com.example.laomao.kotlinplayer.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.laomao.kotlinplayer.widget.LoadMoreView

/**
 * User: laomao
 * Date: 2018-03-06
 * Time: 10-11
 *
 */
abstract class BaseListAdapter<RES>: RecyclerView.Adapter<BaseListAdapter.BaseViewHolder>() {
    var datas=ArrayList<RES>()

    var isShowMore: Boolean = true


    override fun getItemCount(): Int {
        return if (isShowMore) datas.size+1 else datas.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder {
        if (viewType==1&&isShowMore){
            return BaseViewHolder(LoadMoreView(parent?.context))
        }else{
            return BaseViewHolder(getItemView(parent?.context))
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder?, position: Int) {
        if (position==itemCount-1&&isShowMore){
            return
        }
        setItemData(holder,datas,position)
        holder?.itemView?.setOnClickListener {
            listener?.invoke(datas,position)
        }
    }

    /**
     * kotlin中回调接口的写法
     */
    var listener:((List<RES>,Int)->Unit)?=null

    fun setOnItemClickListener(listener:(List<RES>,position:Int)->Unit){
        this.listener=listener
    }

    override fun getItemViewType(position: Int): Int {
        // 如果是最后一个Item，说明是加载更多
        if (position==itemCount-1&&isShowMore){
            return 1
        }else{
            return 0
        }
    }

    class BaseViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {

    }

    /**
     * 设置要展示的数据
     */
    fun setDatas(datas: List<RES>?) {
        datas?.let {
            this.datas.clear()
            this.datas.addAll(datas)
            notifyDataSetChanged()
        }
    }

    /**
     * 设置加载更多的数据
     */
    fun setLoadMoreDatas(datas: List<RES>?){
        datas?.let {
            this.datas.addAll(datas)
            notifyDataSetChanged()
        }
    }

    /**
     * 获取ItemView
     */
    abstract fun getItemView(context: Context?): View

    /**
     * 给ItemView设置数据
     */
    abstract fun setItemData(holder: BaseViewHolder?, datas: ArrayList<RES>, position: Int)

}