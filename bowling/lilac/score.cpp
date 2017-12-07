#include"score.hpp"
using namespace std;
round::round(){
  final_score = 0;
  frameCount = 0;
  frames[0] = 0;
  frames[1] = 0;
  bonus.isActive = false;
  isDone = false;
}

bool round::checkIfBonus(){
  return bonus.isActive;
}

void round::addFrame(const int& frame){  
  if (!checkIfBonus()){
    frames[frameCount] = frame;
    final_score += frame;
    frameCount++;  
    if ((frames[0] + frames[1]) == 10){

      final_score = 0;
      bonus.isActive = true;
      if (frameCount == 1){
	std::cout << "you got a strike" << std::endl;
	bonus.strikeOrSpare = false;
	frameCount--;
      } else {
	bonus.strikeOrSpare = true;
	std::cout << "you got a spare" << std::endl;
	frameCount--;
      }
    } 
  } else {
    int i = bonus.addFrame(frame);
    if (i >= 0) {
      final_score += i;
    }
    frameCount++;
  }
  if (frameCount > 1){
    isDone = true;
  }
}

int round::getFinalScore(){
  return final_score;
}



char round::getFirstBox(){
  if (frames[0]== 0){   
    return '-';
  }
  if (frames[0]== 10){
    return 'X';
  }
  return (char)(frames[0] + 48);
}

char round::getSecondBox(){
  if (frames[0]== 10 || frameCount < 2){
    return ' ';
  }
  if ((frames[0] + frames[1])== 10){
    return '/';
  }
  return (char)(frames[1] + 48);
}


