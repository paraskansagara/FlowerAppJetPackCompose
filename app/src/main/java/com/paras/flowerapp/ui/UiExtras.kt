package com.paras.flowerapp.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.paras.flowerapp.R
import com.paras.flowerapp.theme.appColor
import com.paras.flowerapp.theme.yellow
import com.paras.flowerapp.utils.BottomScreen


@Composable
fun CustomEditBoxWithImageDivider(
    placeHolder: String,
    leadingIconId: Int,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    visualTransformation: VisualTransformation
) {
    val textState = remember { mutableStateOf(TextFieldValue()) }
    var isFocused by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .shadow(
                elevation = if (isFocused) 12.dp else 2.dp,
            )
            .background(Color.White)
            .onFocusChanged { isFocused = it.isFocused },
        value = textState.value,
        onValueChange = { valueChanged ->
            textState.value = valueChanged
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = KeyboardActions(onDone = {
            keyboardController?.hide()
        }),
        placeholder = { Text(text = placeHolder) },
        leadingIcon = {
            Row(
                modifier = Modifier.wrapContentWidth(),
                verticalAlignment = Alignment.CenterVertically,
                content = {
                    Image(
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp)
                            .size(18.dp),
                        bitmap = ImageBitmap.imageResource(id = leadingIconId),  // material icon
                        colorFilter = ColorFilter.tint(appColor),
                        contentDescription = "custom_text_field"
                    )
                    Canvas(
                        modifier = Modifier.height(24.dp)
                    ) {
                        // Allows you to draw a line between two points (p1 & p2) on the canvas.
                        drawLine(
                            color = Color.Black,
                            start = Offset(0f, 0f),
                            end = Offset(0f, size.height),
                            strokeWidth = 2.0F
                        )
                    }
                }
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            cursorColor = Color(0xFFFFB74D)
        ),
        maxLines = 1,
        textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
        visualTransformation = visualTransformation
    )
}

@Composable
fun BottomBar(
    navController: NavHostController,
    screens: List<BottomScreen>
) {

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(64.dp)

    ) {
        NavigationBar(containerColor = colorResource(R.color.splash)) {
            val currentRoute = currentRoute(navController)

            screens.forEach { screen ->
                NavigationBarItem(
                    selected = currentRoute == screen.route,
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Image(
                            painter = painterResource(id = screen.icon),
                            contentDescription = screen.title,
                            modifier = Modifier.size(24.dp),
                            colorFilter = ColorFilter.tint(
                                if (currentRoute == screen.route)
                                    Color.Black
                                else
                                    Color.White
                            )
                        )
                    },
                    label = {

                        Text(screen.title)
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor =yellow,
                        selectedTextColor = Color.White,
                    ),
                    alwaysShowLabel = false
                )
            }
        }

    }

}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}
