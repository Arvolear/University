#include "matrix.hpp"
#include "matrix.cpp"

int main()
{
    Matrix < int > m0(3, 3);

    m0.insert(1, 0, 0);
    m0.insert(2, 2, 2);
    m0.insert(3, 1, 0);
    m0.insert(4, 0, 2);

    cout << "m0 (" << m0.getHeight() << "x" << m0.getWidth() << ")\n" << m0 << endl;
    
    Matrix < int > m1(3, 3);

    m1.insert(5, 1, 1);
    m1.insert(6, 0, 1);
    m1.insert(7, 1, 0);
    m1.insert(8, 2, 1);

    cout << "m1 (" << m1.getHeight() << "x" << m1.getWidth() << ")\n" << m1 << endl;
    
    Matrix < int > m2(3, 3);

    m2.insert(9, 2, 0);
    m2.insert(10, 1, 2);
    m2.insert(11, 2, 2);

    cout << "m2 (" << m2.getHeight() << "x" << m2.getWidth() << ")\n" << m2 << endl;

    Matrix < int > m3 = m0;
    m3 += m1;

    cout << "m3 = m0 + m1 (" << m3.getHeight() << "x" << m3.getWidth() << ")\n" << m3 << endl;

    Matrix < int > m4 = m3.transpose();

    cout << "m4 = m3.transpose() (" << m4.getHeight() << "x" << m4.getWidth() << ")\n" << m4 << endl;

    Matrix < int > m5 = m4;
    m5 *= m3;

    cout << "m5 = m4 + m3 (" << m5.getHeight() << "x" << m5.getWidth() << ")\n" << m5 << endl;

    Matrix < int > m6(2, 3);
    m6.insert(1, 0, 1);
    m6.insert(2, 1, 2);
    m6.insert(3, 1, 1);
    
    cout << "m6 (" << m6.getHeight() << "x" << m6.getWidth() << ")\n" << m6 << endl;

    Matrix < int > m7(3, 2);
    m7.insert(4, 2, 1);
    m7.insert(5, 2, 0);
    m7.insert(6, 1, 1);
    
    cout << "m7 (" << m7.getHeight() << "x" << m7.getWidth() << ")\n" << m7 << endl;

    Matrix < int > m8 = m6 * m7;

    cout << "m8 = m6 * m7 (" << m8.getHeight() << "x" << m8.getWidth() << ")\n" << m8 << endl;

    return 0;
}
