# Tic Tac Toe
<a href="http://developer.android.com/index.html" target="_blank"><img src="https://img.shields.io/badge/platform-android-blue.svg"/></a> <a href="https://android-arsenal.com/api?level=21" target="_blank"><img src="https://img.shields.io/badge/API-21%2B-blue.svg?style=flat"/></a>

Android game using <a href="https://www.journaldev.com/20292/android-mvvm-design-pattern">MVVM</a> design pattern with two modes:

1- Single player mode, the user plays against AI uses a <a href="https://en.wikipedia.org/wiki/Minimax">MiniMax</a> as a decision rule algorithm with <a href="https://en.wikipedia.org/wiki/Alpha%E2%80%93beta_pruning">Alpha Beta Pruning</a> search algorithm.

2- Multi player mode, the user can play against friend locally on the same device.

## Single player mode

Playing against AI with 4 different levels of difficulty, as shown in the image i played the same scenario and each level has different response :

1- Easy defeated with the first 3 moves.

2- Medium defeated with the 4 moves.

3- Hard can not be defeated in this scenario, so i changed the scenario with a harder one the AI lost the game.

### 4- Expert can not be defeated at all.

![alt-tag](https://media.giphy.com/media/LqrJDk6P6HjsPIlK0Z/giphy.gif)

## Libraries used in this project
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata.html)
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel.html)
- [Data binding](https://developer.android.com/topic/libraries/data-binding/index.html)
- [ShapeOfView](https://github.com/florent37/ShapeOfView)
