package com.example.laomao.kotlinplayer.base

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.laomao.kotlinplayer.adapter.BaseListAdapter
import com.example.laomao.kotlinplayer.presenter.impl.BaseListPresenterImpl
import com.example.laomao.kotlinplayer.view.IBaseListView
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * User: laomao
 * Date: 2018-03-06
 * Time: 11-03
 *
 */
abstract class BaseListFragment<RES,R>:BaseFragment(), IBaseListView<RES> {

    val adapter by lazy { getSpecialAdapter() }

    var presenter :BaseListPresenterImpl<RES>?=null

    override fun initView(): View? {
        val view = View.inflate(context, com.example.laomao.kotlinplayer.R.layout.fragment_list, null)
        return view
    }

    override fun initListener() {
        home_refresh_view.setOnRefreshListener { presenter?.loadDatas() }
        home_recycler_view.layoutManager= LinearLayoutManager(context)
        home_recycler_view.adapter= adapter
        home_recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                if (newState== RecyclerView.SCROLL_STATE_IDLE){
                    val layoutManager = recyclerView?.layoutManager
                    if (layoutManager is LinearLayoutManager){
                        val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                        if (lastVisibleItemPosition==adapter.itemCount-1){
                            presenter?.loadMoreDatas()
                        }
                    }
                }
            }
        })
    }

    override fun initData() {
        presenter=getSpecialPresenter()
        presenter?.loadDatas()
    }


    override fun onError(message: String?) {
        message?.let {
            myToast(message)
        }
    }

    override fun onSuccess(r: RES?) {
        adapter.setDatas(getList(r))
        home_refresh_view.isRefreshing=false
    }

    override fun onMoreSuccess(r: RES?) {
        adapter.setLoadMoreDatas(getList(r))
        home_refresh_view.isRefreshing=false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter?.destoryView()
        presenter=null
    }

    /**
     * 获取列表展示数据的list
     */
    abstract fun getList(r: RES?): List<R>?

    /**
     * 获取RecyclerView的Adapter
     */
    abstract fun getSpecialAdapter(): BaseListAdapter<R>

    /**
     * 获取加载数据的Presenter
     */
    abstract fun getSpecialPresenter(): BaseListPresenterImpl<RES>


}