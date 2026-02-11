package project.priceit.feature.my.component

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import project.priceit.core.designsystem.R
import project.priceit.core.designsystem.component.CommonButton
import project.priceit.core.designsystem.component.CommonEditTextBox
import project.priceit.core.designsystem.theme.Black
import project.priceit.core.designsystem.theme.Dimens
import project.priceit.core.designsystem.theme.White

@Composable
fun ProfileDialog(
    currentNickname: String,
    currentImageUrl: String?,
    onDismiss: () -> Unit,
    onConfirm: (String, Uri?) -> Unit
) {
    var nickname by remember { mutableStateOf(currentNickname) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var imageUrl by remember { mutableStateOf(currentImageUrl) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        if (uri != null) {
            imageUri = uri
            imageUrl = null
        }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = White,
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = onDismiss) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "닫기"
                    )
                }
            }
        },
        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(Dimens.DpSmall)
            ) {
                CommonButton(
                    modifier = Modifier.weight(1f),
                    text = "cancel",
                    onClick = { onDismiss() },
                    outlineColor = Black,
                    bgColor = White,
                    textColor = Black
                )

                CommonButton(
                    modifier = Modifier.weight(1f),
                    text = "apply",
                    onClick = { onConfirm(nickname, imageUri) },
                )
            }
        },
        text = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(140.dp)
                        .clip(CircleShape)
                        .clickable { launcher.launch("image/*") }
                ) {
                    when {
                        imageUrl != null -> {
                            AsyncImage(
                                model = imageUrl,
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }

                        imageUri != null -> {
                            AsyncImage(
                                model = imageUri,
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }

                        else -> {
                            Image(
                                painter = painterResource(id = R.drawable.ic_person),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(Dimens.DpLarge))


                CommonEditTextBox(
                    value = nickname,
                    label = "nickname",
                    onValueChange = { nickname = it },
                )
            }
        }
    )
}


@Composable
@Preview
fun ProfileDialogPreview() {
    ProfileDialog(
        currentNickname = "User123",
        currentImageUrl = null,
        onDismiss = {},
        onConfirm = { _, _ -> }
    )
}