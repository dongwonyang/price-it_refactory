package project.priceit.feature.auth.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import project.priceit.core.designsystem.component.CommonButton
import project.priceit.core.designsystem.theme.Dimens
import project.priceit.feature.auth.model.SignUpUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupBottomSheet(
    onDismiss: () -> Unit,
    onSignup: (id: String, pw: String, nick: String) -> Unit,
    signUpUiState: SignUpUiState,
    isValidId: (String) -> Unit,
    isValidPw: (String) -> Unit,
    isValidNick: (String) -> Unit,
) {
    var id by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }
    var nick by remember { mutableStateOf("") }


    ModalBottomSheet(
        onDismissRequest = onDismiss,
        tonalElevation = 4.dp,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = Dimens.CommonPadding)
                .padding(top = Dimens.DpSmall, bottom = Dimens.DpLarge),
            horizontalAlignment = Alignment.Start
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onDismiss) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "뒤로")
                }
                Text(text = "회원가입")
            }

            Spacer(modifier = Modifier.height(Dimens.DpSmall))

            SignUpBox(
                description = "아이디",
                text = id,
                onTextChange = { id = it },
                onButtonClick = isValidId,
                responseMsg = signUpUiState.idMsg
            )
            Spacer(modifier = Modifier.height(Dimens.DpMedium))

            SignUpBox(
                description = "비밀번호",
                text = pw,
                onTextChange = { pw = it },
                onButtonClick = isValidPw,
                responseMsg = signUpUiState.pwMsg
            )
            Spacer(modifier = Modifier.height(Dimens.DpMedium))

            SignUpBox(
                description = "닉네임",
                text = pw,
                onTextChange = { id = it },
                onButtonClick = isValidNick,
                responseMsg = signUpUiState.nicknameMsg
            )
            Spacer(modifier = Modifier.height(Dimens.DpMedium))


            CommonButton(
                modifier = Modifier.fillMaxWidth(),
                text = "회원가입",
                onClick = {
                    onSignup(id, pw, nick)
                    onDismiss()
                },
                enabled = signUpUiState.run {
                    !((idMsg.errorState ?: true) && (pwMsg.errorState
                        ?: true) && (nicknameMsg.errorState ?: true))
                },
            )
        }
    }
}
