#pragma once


class Bonus {
public:
  Bonus();  
  int addFrame(const int& frame);
  bool isActive;
  bool strikeOrSpare;
private:

  int frameCount;  
};




