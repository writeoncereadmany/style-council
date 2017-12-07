#include"bonus.hpp"
#include<iostream>
Bonus::Bonus(){
  frameCount = -1;
  isActive = false;
  strikeOrSpare = false;
}

int Bonus::addFrame(const int& frame){
  frameCount++;

  if (!strikeOrSpare){

    switch(frameCount) {
    case 0 : 
      return 10 + frame;
      break;
    case 1 : 
      return frame;
      break;
    default : 
      return -1;
    } 
  }else {
    if (frameCount == 0){
      return 10 +frame;
    } else {
      return -1;
    }
    
  }

  return 0;
}


