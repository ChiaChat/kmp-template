package com.chiachat.kmp.template.shared.ui.components.homefeed

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.chiachat.kmp.template.shared.model.user.Post
import com.chiachat.kmp.template.shared.ui.composables.Graphics.GraphicButton
import com.chiachat.kmp.template.shared.ui.theme.AppGraphics

internal object HomeFeed {
    @Composable
    fun HomeFeedScreen(posts: List<Post>) {
        TopNavBar(menuClick = {}, homeClick = {}, notificationsClick = {})
        LazyColumn { posts.forEach { post -> item { Post(post) } } }
    }

    @Composable
    fun TopNavBar(
        modifier: Modifier = Modifier,
        menuClick: () -> Unit,
        homeClick: () -> Unit,
        notificationsClick: () -> Unit
    ) {
        Row(
            modifier = modifier.fillMaxWidth().height(64.dp).padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MenuButton(menuClick)
            HomeButton(homeClick)
            NotificationButton(notificationsClick)
        }
    }

    @Composable
    fun MenuButton(onClick: () -> Unit) {
        GraphicButton(graphic = AppGraphics.MENU, contentDescription = "menu", onClick = onClick)
    }

    @Composable
    fun HomeButton(onClick: () -> Unit) {
        GraphicButton(
            graphic = AppGraphics.DIALEXA_ICON,
            contentDescription = "home",
            onClick = onClick
        )
    }

    @Composable
    fun NotificationButton(onClick: () -> Unit) {
        GraphicButton(
            graphic = AppGraphics.NOTIFICATION_BELL,
            contentDescription = "notifications",
            onClick = onClick
        )
    }

    @Composable
    fun Post(post: Post) {
        Surface(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.padding(16.dp)) {
                /* Avatar image */
                Column(modifier = Modifier.padding(start = 16.dp)) {
                    /* User name and handle */
                    Row {
                        Text(text = post.user.name, style = TextStyle(fontWeight = FontWeight.Bold))
                        Text(text = post.user.handle, style = TextStyle(color = Color.Gray))
                    }
                    /* Tweet text */
                    Text(text = post.text)
                    /* Tweet metadata (time, likes, replies, etc.) */
                    Row {
                        Text(text = post.timestamp, style = TextStyle(color = Color.Gray))
                        /* Other tweet metadata */
                    }
                }
            }
        }
    }
}
