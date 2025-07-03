package com.chinky.family.presentation.ui.composableFunctions.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chinky.family.presentation.ui.theme.ApplicationColor
import com.chinky.family.presentation.ui.theme.ApplicationTheme

class BottomNavigationDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApplicationTheme.HomeApplicationTheme {
                Scaffold { paddingValues ->
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
                        CreateBottomNavigation(paddingValues)
                    }
                }
            }
        }
    }

    @Composable
    private fun CreateBottomNavigation(paddingValues: PaddingValues) {
        var selectedIndex by remember { mutableIntStateOf(0) }
        AnimatedBottomNavigation(
            items = listOf(
                NavigationItem("Home", Icons.Default.Home),
                NavigationItem("Search", Icons.Default.Search),
                NavigationItem("Notifications", Icons.Default.Notifications)
            ),
            selectedIndex = selectedIndex,
            onItemSelected = { index ->
                selectedIndex = index
            },
            modifier = Modifier.padding(paddingValues)
        )
    }

    @Composable
    fun AnimatedBottomNavigation(
        items: List<NavigationItem>,
        selectedIndex: Int,
        onItemSelected: (Int) -> Unit,
        modifier: Modifier = Modifier
    ) {
        val animatedSelectedIndex by animateIntAsState(
            targetValue = selectedIndex,
            animationSpec = tween(
                durationMillis = 300,
                easing = FastOutSlowInEasing
            ),
            label = "selectedIndex"
        )

        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(32.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFFF8FAFC),
                                Color(0xFFE2E8F0)
                            )
                        )
                    )
                    .padding(vertical = 0.dp, horizontal = 8.dp)
            ) {
                // Background indicator
                AnimatedIndicator(
                    selectedIndex = animatedSelectedIndex,
                    itemCount = items.size,
                    modifier = Modifier.fillMaxWidth()
                )

                // Navigation items
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    items.forEachIndexed { index, item ->
                        NavigationItemContent(
                            item = item,
                            isSelected = index == selectedIndex,
                            onClick = { onItemSelected(index) },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun AnimatedIndicator(
        selectedIndex: Int,
        itemCount: Int,
        modifier: Modifier = Modifier
    ) {
        val configuration = LocalConfiguration.current
        val screenWidth = configuration.screenWidthDp.dp
        val indicatorWidth = (screenWidth - 64.dp) / itemCount // Accounting for padding

        val animatedOffset by animateDpAsState(
            targetValue = indicatorWidth * selectedIndex,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            ),
            label = "indicatorOffset"
        )

        Box(
            modifier = modifier
                .offset(x = animatedOffset)
                .width(indicatorWidth)
                .height(56.dp)
                .clip(RoundedCornerShape(28.dp))
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF667EEA),
                            Color(0xFF764BA2)
                        )
                    )
                )
        )
    }

    @Composable
    private fun NavigationItemContent(
        item: NavigationItem,
        isSelected: Boolean,
        onClick: () -> Unit,
        modifier: Modifier = Modifier
    ) {
        val interactionSource = remember { MutableInteractionSource() }

        val scale by animateFloatAsState(
            targetValue = if (isSelected) 1.1f else 1f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            ),
            label = "scale"
        )

        val iconTint by animateColorAsState(
            targetValue = if (isSelected) ApplicationColor.White else ApplicationColor.Black,
            animationSpec = tween(durationMillis = 200),
            label = "iconTint"
        )

        val labelColor by animateColorAsState(
            targetValue = if (isSelected) ApplicationColor.White else ApplicationColor.Black,
            animationSpec = tween(durationMillis = 200),
            label = "labelColor"
        )

        Column(
            modifier = modifier
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) { onClick() }
                .scale(scale)
                .padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box {
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.label,
                    tint = iconTint,
                    modifier = Modifier.size(24.dp)
                )

                // Badge
                item.badgeCount?.let { count ->
                    AnimatedBadge(
                        count = count,
                        modifier = Modifier
                            .offset(x = 8.dp, y = (-8).dp)
                            .align(Alignment.TopEnd)
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            AnimatedVisibility(
                visible = isSelected,
                enter = fadeIn(animationSpec = tween(200)) +
                        slideInVertically(
                            animationSpec = tween(200),
                            initialOffsetY = { it / 2 }
                        ),
                exit = fadeOut(animationSpec = tween(200)) +
                        slideOutVertically(
                            animationSpec = tween(200),
                            targetOffsetY = { it / 2 }
                        )
            ) {
                Text(
                    text = item.label,
                    color = labelColor,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }

    @Composable
    private fun AnimatedBadge(
        count: Int,
        modifier: Modifier = Modifier
    ) {
        val scale by animateFloatAsState(
            targetValue = if (count > 0) 1f else 0f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessHigh
            ),
            label = "badgeScale"
        )

        val pulseScale by rememberInfiniteTransition(label = "badgePulse").animateFloat(
            initialValue = 1f,
            targetValue = 1.2f,
            animationSpec = infiniteRepeatable(
                animation = tween(1000, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            ),
            label = "pulseScale"
        )

        Box(
            modifier = modifier
                .scale(scale * pulseScale)
                .size(18.dp)
                .clip(CircleShape)
                .background(Color(0xFFEF4444)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (count > 99) "99+" else count.toString(),
                color = Color.White,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }

    // Example usage with sample data
    @Composable
    fun BottomNavigationExample() {
        var selectedIndex by remember { mutableIntStateOf(0) }

        val navigationItems = listOf(
            NavigationItem("Home", Icons.Default.Home),
            NavigationItem("Search", Icons.Default.Search),
            NavigationItem("Notifications", Icons.Default.Notifications, badgeCount = 3),
            NavigationItem("Messages", Icons.Default.Email, badgeCount = 12),
            NavigationItem("Profile", Icons.Default.Person)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF667EEA),
                            Color(0xFF764BA2)
                        )
                    )
                )
        ) {
            // Your main content goes here
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        when (selectedIndex) {
                            0 -> {
                                Icon(
                                    Icons.Default.Home,
                                    contentDescription = null,
                                    modifier = Modifier.size(48.dp),
                                    tint = Color(0xFF667EEA)
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = "🏠 Home",
                                    style = MaterialTheme.typography.headlineMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Welcome to your beautiful home dashboard",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = Color(0xFF64748B)
                                )
                            }

                            1 -> {
                                Icon(
                                    Icons.Default.Search,
                                    contentDescription = null,
                                    modifier = Modifier.size(48.dp),
                                    tint = Color(0xFF667EEA)
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = "🔍 Search",
                                    style = MaterialTheme.typography.headlineMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Discover amazing content with powerful search",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = Color(0xFF64748B)
                                )
                            }

                            2 -> {
                                Icon(
                                    Icons.Default.Notifications,
                                    contentDescription = null,
                                    modifier = Modifier.size(48.dp),
                                    tint = Color(0xFF667EEA)
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = "🔔 Notifications",
                                    style = MaterialTheme.typography.headlineMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Stay updated with important notifications",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = Color(0xFF64748B)
                                )
                            }

                            3 -> {
                                Icon(
                                    Icons.Default.Email,
                                    contentDescription = null,
                                    modifier = Modifier.size(48.dp),
                                    tint = Color(0xFF667EEA)
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = "💬 Messages",
                                    style = MaterialTheme.typography.headlineMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Connect with friends and family",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = Color(0xFF64748B)
                                )
                            }

                            4 -> {
                                Icon(
                                    Icons.Default.Person,
                                    contentDescription = null,
                                    modifier = Modifier.size(48.dp),
                                    tint = Color(0xFF667EEA)
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = "👤 Profile",
                                    style = MaterialTheme.typography.headlineMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Manage your account and preferences",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = Color(0xFF64748B)
                                )
                            }
                        }
                    }
                }
            }

            // Bottom Navigation
            AnimatedBottomNavigation(
                items = navigationItems,
                selectedIndex = selectedIndex,
                onItemSelected = { selectedIndex = it }
            )
        }
    }

    data class NavigationItem(
        val label: String,
        val icon: ImageVector,
        val badgeCount: Int? = null
    )
}