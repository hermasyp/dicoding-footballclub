package isfaaghyth.app.fotballclub.ui.main.fragment.favteam

import android.support.v7.widget.GridLayoutManager
import isfaaghyth.app.fotballclub.R
import isfaaghyth.app.fotballclub.base.BaseFragment
import isfaaghyth.app.fotballclub.data.model.Team
import isfaaghyth.app.fotballclub.data.model.Teams
import isfaaghyth.app.fotballclub.ui.adapter.TeamAdapter
import isfaaghyth.app.fotballclub.utils.reactive.AppSchedulerProvider
import kotlinx.android.synthetic.main.fragment_fav_player.*

/**
 * Created by isfaaghyth on 9/21/18.
 * github: @isfaaghyth
 */
class FavTeamFragment : BaseFragment<FavTeamPresenter>(), FavTeamView {

    override fun presenter(): FavTeamPresenter = FavTeamPresenter(this, AppSchedulerProvider())
    override fun contentView(): Int = R.layout.fragment_fav_player

    private var teams: MutableList<Team> = mutableListOf()
    private var adapter = TeamAdapter(teams)

    override fun onCreated() {
        lstFavPlayer.layoutManager = GridLayoutManager(context(), 3)
        lstFavPlayer.adapter = adapter
        swipeRefresh.setOnRefreshListener { getFavoriteLocal() }
        swipeRefresh.post {
            swipeRefresh.isRefreshing = true
            getFavoriteLocal()
        }
    }

    private fun getFavoriteLocal() {
        teams.clear()
        presenter().getTeam(context())
        swipeRefresh.isRefreshing = false
    }

    override fun getTeam(team: Teams) {
        swipeRefresh.isRefreshing = false //TODO(harusnya masuk ke basePresenter)
        teams.add(team.teams[0])
        adapter.notifyDataSetChanged()
    }

}