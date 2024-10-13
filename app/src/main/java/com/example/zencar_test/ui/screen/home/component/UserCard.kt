package com.example.zencar_test.ui.screen.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zencar_test.R
import com.example.zencar_test.domain.model.User
import com.example.zencar_test.ui.screen.registration.components.UserAvatar
import com.example.zencar_test.ui.theme.SecondaryCyan
import com.example.zencar_test.utils.clickableWithRipple
import com.example.zencar_test.utils.formatDate
import com.example.zencar_test.utils.formatDateAndTime

@Preview(
    showSystemUi = true,
)
@Composable
private fun UserCardPreview() {
    UserCard(
        user = User.mock,
        isDelete = true,
        onClickDelete = {}
    )
}

@Composable
fun UserCard(
    modifier: Modifier = Modifier,
    user: User,
    isDelete: Boolean,
    onClickDelete: () -> Unit,
) {
    ElevatedCard(
        modifier = modifier
            .padding(
                horizontal = 16.dp
            ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 3.dp
        )
    ) {
        Row(
            modifier = Modifier
                .padding(
                    horizontal = 8.dp,
                    vertical = 16.dp
                ),
        ) {
            UserAvatar(
                modifier = Modifier
                    .size(54.dp),
                imageUri = user.img,
            )
            Column(
                modifier = Modifier
                    .padding(start = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = user.name,
                        color = if (user.isCurrent) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onBackground
                    )
                    if (!user.isCurrent) {
                        Icon(
                            modifier = Modifier
                                .clickableWithRipple(
                                    onClick = {
                                        onClickDelete()
                                    },
                                    enabled = isDelete
                                ),
                            imageVector = Icons.Rounded.Delete,
                            tint = if (isDelete) MaterialTheme.colorScheme.primary else Color.Gray,
                            contentDescription = null,
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = formatDate(user.birthday),
                        fontSize = 12.sp,
                        color = if (user.isCurrent) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = stringResource(
                            id = R.string.date_created,
                            formatDateAndTime(user.dateCreated)
                        ),
                        fontSize = 10.sp,
                        color = if (user.isCurrent) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }
}