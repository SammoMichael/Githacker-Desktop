package com.example.demo.view

import com.example.demo.app.Styles
import com.example.demo.data.Repo
import com.example.demo.network.GithubApi
import com.example.demo.network.NetworkService
import com.sun.tools.javac.jvm.Code.width
import javafx.application.Platform
import javafx.scene.control.Button
import javafx.scene.control.ScrollPane
import javafx.scene.image.Image
import javafx.scene.layout.VBox
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tornadofx.*
import javax.swing.Spring.height


class MainView : View("GitHacker Desktop") {
    override val root = hbox {
        label(title) {
            addClass(Styles.heading)
        }
    }

    init {
        val service: GithubApi = NetworkService().retrofit.create(GithubApi::class.java)
        service.listRepos().enqueue(object : Callback<List<Repo>> {
            override fun onFailure(p0: Call<List<Repo>>, p1: Throwable) {
                print("Failure")
            }

            override fun onResponse(p0: Call<List<Repo>>, p1: Response<List<Repo>>) {
                Platform.runLater {
                    val vbox: VBox = vbox()

                    p1.body()?.forEach {
                        val (id, full_name, html_url) = it
                        vbox += hyperlink {
                            action { hostServices.showDocument(it.html_url) }
                            text = full_name.toString()

                        }
                        print(it.owner.avatar_url)
//                            vbox += imageview(it.owner.avatar_url){
//                                scaleX = .10
//                                scaleY = .10
//                            }
                    }
                }
            }
        })
    }
}