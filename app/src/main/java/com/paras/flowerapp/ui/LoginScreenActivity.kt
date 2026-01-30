package com.paras.flowerapp.ui


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.paras.flowerapp.R
import com.paras.flowerapp.app.App
import com.paras.flowerapp.theme.Pink40
import com.paras.flowerapp.theme.appColor
import com.paras.flowerapp.utils.Const

class LoginScreenActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen()
        }
    }
}



@Composable
fun LoginScreen() {
    val bottomPadding = WindowInsets.systemBars.asPaddingValues().calculateBottomPadding()
    val context = LocalContext.current
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .padding(bottom = bottomPadding)
            .background(color = Color.White)
    )
    {
        val (loginCard, imageId, boxImage, signUpTextView,bottomBox) = createRefs()


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.White)
                .constrainAs(boxImage) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {

            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .align(Alignment.TopStart)
            ) {
                val wavePath = Path().apply {
                    moveTo(0f, 150f)
                    cubicTo(
                        size.width / 2, 200f,
                        5 * size.width / 8, 500f,
                        size.width, 150f
                    )
                    lineTo(size.width, 0f)
                    lineTo(0f, 0f)
                    close()
                }
                drawPath(
                    path = wavePath,
                    brush = SolidColor(appColor),
                    style = androidx.compose.ui.graphics.drawscope.Fill,

                    )
            }

        }



        Image(
            modifier = Modifier
                .wrapContentSize()
                .constrainAs(imageId) {
                    top.linkTo(parent.top, 30.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            bitmap = ImageBitmap.imageResource(id = R.drawable.app_logo),
            contentScale = ContentScale.FillWidth,
            contentDescription = "header_view_login_bg"
        )

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
            .constrainAs(loginCard) {
                top.linkTo(imageId.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(bottomBox.top)
            }
            .padding(10.dp)
            .fillMaxWidth()

        ) {

            Text(
                text = "Login",
                fontSize = 30.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )


            Text(
                text = "Email Address",
                style = MaterialTheme.typography.labelMedium.copy(color = Pink40),
                modifier = Modifier.padding(bottom = 10.dp, top = 10.dp)
            )



            CustomEditBoxWithImageDivider(
                "Email Address",
                android.R.drawable.ic_dialog_email,
                KeyboardType.Email,
                ImeAction.Next,
                VisualTransformation.None
            )

            Text(
                text = "Password",
                style = MaterialTheme.typography.labelMedium.copy(color = Pink40),
                modifier = Modifier.padding(bottom = 10.dp, top = 10.dp)
            )

            CustomEditBoxWithImageDivider(
                "Password",
                android.R.drawable.ic_lock_lock,
                KeyboardType.Password,
                ImeAction.Done,
                PasswordVisualTransformation()
            )



            Button(
                onClick = {
                    App.instance.sharedPrefUtils.putBoolean(Const.KEY_IS_LOGIN,true)
                    context.startActivity(Intent(context, MainActivity::class.java))
                   (context as LoginScreenActivity).finish()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .padding(top = 20.dp)
                    .align(Alignment.End)
                    .height(50.dp)
                    .width(150.dp)
                    .background(
                        Brush.horizontalGradient(
                            listOf(Color(0xFFFFD740), Color(0xFFFFA726))
                        ),
                        shape = RoundedCornerShape(50)
                    )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                ) {

                    Text(
                        text = "LOGIN",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.align(Alignment.CenterStart)
                    )

                    Image(
                        alignment = Alignment.CenterEnd,
                        painter = painterResource(id = R.drawable.ic_right_arrow), // Replace with your arrow resource
                        contentDescription = "Right Arrow",
                        modifier = Modifier
                            .size(30.dp)
                            .align(Alignment.CenterEnd), // Align to the end of the button
                        contentScale = ContentScale.Fit,
                        colorFilter = ColorFilter.tint(Color.White)

                    )
                }
            }
        }


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .constrainAs(bottomBox) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .align(Alignment.TopStart)
            ) {
                val wavePath = Path().apply {
                    // Start at the bottom-left corner
                    moveTo(0f, 0f)
                    cubicTo(
                        size.width / 2, -500f, // Control point 1
                        5 * size.width / 8, -200f, // Control point 2
                        size.width, 0f // End point
                    )
                    lineTo(size.width, size.height) // Line to bottom-right corner
                    lineTo(0f, size.height) // Line to bottom-left corner
                    close()
                }
                drawPath(
                    path = wavePath,
                    brush = SolidColor(appColor),
                    style = androidx.compose.ui.graphics.drawscope.Fill,
                )
            }
        }

        SignUpText(Modifier.constrainAs(signUpTextView) {
            bottom.linkTo(parent.bottom, margin = 10.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        },context)

    }
}

@Composable
fun SignUpText(modifier: Modifier, context: Context) {
    val annotatedString = buildAnnotatedString {
        // Regular text
        append("Don't have an account? ")

        // Sign up text with clickable style
        pushStringAnnotation(
            tag = "SIGN_UP", // Tag for identifying the click action
            annotation = "Sign up"
        )
        withStyle(style = SpanStyle(color =Color.White, fontWeight = FontWeight.Bold)) {
            append("Sign up")
        }
        pop()
    }

    Text(
        text = annotatedString,
        style = TextStyle(fontSize = 16.sp, color = Color.Black),
        modifier = modifier.clickable {
            context.startActivity(Intent(context, SignUpActivity::class.java))
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LoginScreen()
}
