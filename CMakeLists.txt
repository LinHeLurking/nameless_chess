# CMakeList.txt : CMake project for nameless_chess_cpp, include source and define
# project specific logic here.
#
cmake_minimum_required (VERSION 3.8)

# Add source to this project's executable.
add_executable (nameless_chess_cpp "src/test_main.cpp" "src/matrix.h" "src/Chess_Board.h" "src/Chess_Board.cpp" "src/game.h" "src/game.cpp" "src/message.h" "src/dep.h")

# TODO: Add tests and install targets if needed.

set(CMAKE_SUPPRESS_REGENERATION true)
