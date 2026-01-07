package project.priceit.feature.auth.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import project.priceit.core.designsystem.component.CommonEditTextBox

@Composable
fun LoginEditTextBox(
    modifier: Modifier = Modifier,
    description: String,
    text: String,
    onTextChange: (String) -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = description,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleSmall
        )
        CommonEditTextBox(
            value = text,
            onValueChange = { onTextChange(it) },
        )
    }
}

@Preview
@Composable
fun LoginEditTextBoxPreview() {
    LoginEditTextBox(
        description = "Email",
        text = "",
        onTextChange = {}
    )
}