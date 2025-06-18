# Here is the list of all the basic composable used in Jetpack Compose

- Text & Input
  - Text() : Used to display text.
  - TextField() : Allows user input.
  - OutlinedTextField() : A bordered text input field.
- Buttons & Clickables
  - Button() : Standard button.
  - OutlinedButton() : Button with an outline.
  - TextButton(): Button with Text stying.
  - IconButton(): Button with an Icon.
  - FilledIconButton(): Button with a filled icon.
  - FilledTonalIconButton(): Button with a tonal filled icon.
  - OutlinedIconButton(): Button with an outlined icon.
  - IconToggleButton(): Button with an icon toggle.
  - FloatingActionButton(): A floating action button.
  - ElevatedButton(): A button with a filled background.
  - FilledTonalButton(): A button with a tonal filled background.
- Layouts & Containers
  - Column() : Arranges elements vertically.
  - Row() : Arranges elements horizontally.
  - Box() : Overlays elements.
  - LazyColumn() : Optimized Vertical List.
  - LazyRow() : Optimized Horizontal List.
  - Scaffold() : Provides a basic app structure.
  - ModalNavigationDrawer() : Used to show drawer
  - TopAppBar() : Displays a top app bar.
- Selection & Toggles
  - Checkbox() : A checkbox for selection.
  - Switch() : A toggle switch.
  - RadioButton() : A radio button for selection.
  - Slider()
  - RangeSlider()
- Cards & Lists
  - Card() : Displays content in a card format.
  - ListItem() : Represents an item in a list.
- Progress & Indicators
  - CircularProgressIndicator() : Circular loading Indicator.
  - LinearProgressIndicator() : Linear loading bar.
- Dialogs & Popups
  - AlertDialog() : Displays an alert dialog.
  - Snackbar() : Shows a brief message.
- Images and Icons
  - Image() : Displays an image.
  - Icon() : Displays an icon.
- Modifiers & Styling
  - Modifier() : Used to style and position elements.


# State Management Options

## State Holders
- remember() - remembers state across recompositions but do not survive configuration changes.
- rememberSaveable() - remembers state across recompositions and also survives configuration changes.
- rememberCoroutineScope() - Provides a coroutine scope tied to the composable's lifecycle.
- rememberUpdatedState() - Captures and updates values in effects without restarting them.
- derivedStateOf() - Creates state that derives from other state values, only recomposes when the result changes.
- produceState() - Converts non-compose state sources into Compose state.

## ViewModel Integration
- viewModel() - Create or retrieve a ViewModel scoped to the current composable.
- hiltViewModel() - For dependency injection with hilt.
- collectAsState() - Observes StateFlow/Flow from ViewModels as Compose state.
- collectAsStateWithLifecycle() - Lifecycle-aware collection of flows.

## External State Management
- observeAsState() - Used for observing LiveData
- subscribeAsState() - Used for observing RxJava observables.

## Specialized States
- rememberScrollState() - Used for scroll position management.
- rememberLazyListState() - Used for LazyColumn/LazyRow state.
- rememberPagerState() - Used for Pager components.
- rememberBottomSheetState() - Used for BottomSheet state.
- rememberDrawerState() - Used for navigation drawer state.
- rememberGraphicsLayer()

## Core Animation States

### Basic animation States
- animateFloatAsState() - Animates a single float value.
- animateIntAsState() - Animates a single integer value.
- animateColorAsState() - Animates color values.
- animateDpAsState() - Animates Dp values.
- animateOffsetAsState() - Animates Offset values.
- animateRectAsState() - Animates Rect values.
- animateSizeAsState() - Animates Size values.
- animateIntOffsetAsState() - Animates IntOffset values.
- animateIntSizeAsState() - Animates IntSize values.
  
### Multi value animation states

- updateTransition() - Creates a transition for multiple animated values.
- rememberInfiniteTransition() - For infinitely repeating animations
- animateValueAsState() - Generic animation for any animatable type
  
### Transition - Based States

- Transition.animateFloat() - Float animation within a transition.
- Transition.animateColor() - Color animation within a transition.
- Transition.animateDp() - Dp animation within a transition.
- Transition.animateOffset() - Offset animation within a transition.
- Transition.animateRect() - Rect animation within a transition.
- Transition.animateSize() - Size animation within a transition.
- Transition.animateValue() - Generic animation within a transition.

### Infinite Animation States

- InfiniteTransition.animateFloat() - Float animation with infinite duration.
- InfiniteTransition.animateColor() - Color animation with infinite duration.
- InfiniteTransition.animateValue() - Value animation with infinite duration.

### Decay and Physics Based Animations

- rememberSplineBasedDecay() - Spline based decay animation state.
- DecayAnimationSpec - For decay animations
- SpringSpec - Spring-based animation specifications.
- TweenSpec - Tween-based animation specifications.
- KeyFramesSpec - Keyframe-based animation specifications.
- RepeatableSpec - Repeatable animation specifications.
- InfiniteRepeatableSpec - Infinite repeatable animation specifications.
- SnapSpec - Instant snap animation specifications.

### Layout Animation States

- Animatable - Animates any value.
- AnimationState - Holds animation state information.
- AnimationScope - Scope for animation operations.
- VectorizedAnimationSpec - For vectorized animations.

### Layout animation states

- AnimatedVisibility - Built-in enter/exit animations.
- AnimatedContent - Content Switching animations.
- Crossfade - Cross-fade between content.
- slideInHorizontally/slideOutHorizontally - Horizontal slide animations.
- slideInVertically/slideOutVertically - Vertical slide animations.
- fadeIn/fadeOut - Fade animations
- scaleIn/scaleOut - Scale animations.
- expandIn/shrinkOut - Expand/Shrink animations
- expandHorizontally/shrinkHorizontally - Horizontal expand/shrink animations.
- expandVertically/shrinkVertically - Vertical expand/shrink animations.

### Navigation Animation States

- AnimatedNavHost - Navigation with animations.
- enterTransition/exitTransition - Navigation transition states.
- popEnterTransition/popExitTransition - Pop navigation transitions.

### Custom Animation States

- AnimatableVector - For vector-based animations.
- TwoWayConverter - Converts between types for animation.
- AnimationVector - Base class for animation vectors.
- AnimationSpec - Base interface for animation specifications.

### Specialized Animation Statess

- rememberScrollState() - With animated scrolling capabilities
- LazyListState - With animateScrollToItem() and animateItemPlacement()
- PagerState - With animateScrollToPage()
- DrawerState - With animated open/close.
- BottomSheetState - With animated expand/collapse.
- SwipeableState - For swipeable components
- DraggableState - For draggable components
