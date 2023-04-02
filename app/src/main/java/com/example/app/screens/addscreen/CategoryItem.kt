package com.example.app.screens.addscreen

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.ArrowRight
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app.screens.addscreen.models.DeviceCategory
import com.example.app.ui.theme.Purple282832
import com.example.app.ui.theme.TextColor
import com.example.app.ui.theme.White

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    deviceCategory: DeviceCategory
) {
    var isOpened by remember {
        mutableStateOf(false)
    }
    Surface(
        modifier = Modifier
            .padding(
                vertical = 6.dp
            )
            .fillMaxWidth()
            .clickable {
                isOpened = !isOpened
            },
        color = Purple282832
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 12.dp,
                    bottom = 12.dp,
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 48.dp,
                        end = 12.dp
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = deviceCategory.title,
                    color = White,
                    fontSize = 16.sp
                )

                Icon(
                    modifier = Modifier.size(20.dp),
                    imageVector =
                    if (isOpened) Icons.Outlined.ArrowDropDown
                    else Icons.Outlined.ArrowRight,
                    tint = White,
                    contentDescription = null
                )

            }
            if (isOpened) {
                Column(modifier = Modifier
                    .animateContentSize(animationSpec = tween(250))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) { isOpened = !isOpened }) {

                    Column() {
                        deviceCategory.deviceList.forEach {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    modifier = Modifier.padding(vertical = 10.dp, horizontal = 16.dp),
                                    text = it,
                                    color = TextColor,
                                    fontSize = 16.sp
                                )
                            }

                        }
                    }

                }
            }
        }

    }
}
