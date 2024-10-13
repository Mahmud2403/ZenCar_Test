package com.example.zencar_test.ui.screen.registration.components

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.zencar_test.R


@Composable
fun UserAvatar(
    modifier: Modifier = Modifier,
    imageUri: Any? = null,
    onClickPickImage: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .size(150.dp)
            .clip(CircleShape)
            .background(Color.Gray)
    ) {
        if (imageUri != null) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        onClickPickImage()
                    },
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUri)
                    .crossfade(enable = true)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .memoryCachePolicy(CachePolicy.DISABLED)
                    .build(),
                contentDescription = "Avatar Image",
                contentScale = ContentScale.Crop,
            )
        } else {
            Image(
                modifier = Modifier
                    .clickable {
                        onClickPickImage()
                    },
                painter = painterResource(id = R.drawable.image_user_placeholder),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
    }
}