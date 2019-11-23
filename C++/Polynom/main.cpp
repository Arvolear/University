#include "polynom.hpp"
#include "polynom.cpp"

int main()
{
    Polynom < int > p0;

    p0.insert(6, 1);
    p0.insert(2, 4);
    p0.insert(-9, 0);
    p0.insert(1, 6);
    
    cout << "p0\n"<< p0 << endl << endl;
    cout << p0.size() << endl;
    
    Polynom < int > p1;

    p1.insert(1, 3);
    p1.insert(6, 1);
    p1.insert(3, 0);
    
    cout << "p1\n" << p1 << endl << endl;
    cout << p1.size() << endl;

    Polynom < int > dummy = p0 + p1;

    cout << "dummy = p0 + p1\n" << dummy << endl << endl;
    cout << dummy.size() << endl;
    
    dummy = p0 - p1;

    cout << "dummy = p0 - p1\n" << dummy << endl << endl;

    dummy = p0 * p1;

    cout << "dummy = p0 * p1\n" << dummy << endl << endl;
    
    dummy = p0 / p1;

    cout << "dummy = p0 / p1\n" << dummy << endl << endl;

    dummy.derivate(1);
    
    cout << "dummy.derivate(1)\n" << dummy << endl;

    return 0;
}
