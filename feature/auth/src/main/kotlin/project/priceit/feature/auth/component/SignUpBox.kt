package project.priceit.feature.auth.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import project.priceit.core.designsystem.component.CommonButton
import project.priceit.core.designsystem.component.CommonEditTextBox
import project.priceit.core.designsystem.theme.Black50
import project.priceit.core.designsystem.theme.Dimens
import project.priceit.core.designsystem.theme.Red
import project.priceit.feature.auth.model.ResponseMsg

@Composable
fun SignUpBox(
    modifier: Modifier = Modifier,
    description: String,
    text: String,
    onTextChange: (String) -> Unit,
    onButtonClick: (String) -> Unit,
    responseMsg: ResponseMsg
) {
    Box(
        modifier = modifier
    ) {
        Column{
            Text(
                text = description,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleSmall
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CommonEditTextBox(
                    value = text,
                    onValueChange = onTextChange,
                    modifier = Modifier.weight(1f)
                )

                Spacer(Modifier.width(Dimens.DpSmall))

                CommonButton(
                    text = "확인",
                    onClick = { onButtonClick(text) },
                    modifier = Modifier
                        .height(Dimens.TexBoxHeight - 12.dp)
                        .wrapContentWidth()
                )
            }

            Spacer(Modifier.height(Dimens.DpSmall))

            responseMsg.errorState?.let { isError ->
                Text(
                    text = responseMsg.message, color = if (isError) Red else Black50
                )
            }
        }
    }
}

@Composable
@Preview
fun SignUpBoxPreview() {
    SignUpBox(
        description = "Email",
        text = "",
        onTextChange = {},
        onButtonClick = {},
        responseMsg = ResponseMsg("example", true)
    )
}