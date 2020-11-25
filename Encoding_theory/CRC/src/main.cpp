#include "./CRC/Driver.hpp"

const string Driver::POLYNOM = "10001001"; // x^7 + x^3 + 1
const int Driver::BUFFER = 30;

int main(int argc, char **argv)
{	    
    Driver driver;    

    driver.work(argc, argv);

    return 0;
}
