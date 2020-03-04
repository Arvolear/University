#include <iostream>
#include <string>
#include <vector>

using namespace std;

void recursion(int n, int k, int d, string ans, bool &possible)
{
    if (d == k + 1)
    {
        long long num;
        string tmp = "";
        int coord;

        for (int i = 0; i < (int)ans.length(); i++)
        {
            if (int(ans[i]) >= int('0') && int(ans[i]) <= int('9'))
            {
                tmp += ans[i];
                coord = i;
            }
            else
            {
                break;
            }
        }
        
        num = stoll(tmp);
        tmp = "";
        
        coord++;
        for (int i = coord; i < (int)ans.length(); i++)
        {
            if (ans[i] == '+' || ans[i] == '-')
            {
                for (int j = i + 1; j < (int)ans.length(); j++)
                {
                    if (int(ans[j]) >= int('0') && int(ans[j]) <= int('9'))
                    {
                        tmp += ans[j];
                        coord = j;
                    }
                    else
                    {
                        break;
                    }
                }

                if (ans[i] == '+')
                {
                    num += stoll(tmp);
                }
                if (ans[i] == '-')
                {
                    num -= stoll(tmp);
                }

                tmp = "";
            }
        }

        if (num == n)
        {
            cout << ans << "=" << n << endl;
            possible = true;
        }

        return;
    }


    for (int i = 0; i < 3; i++)
    {
        if (i == 0)
        {
            recursion(n, k, d + 1, ans + to_string(d), possible); 
        }
        if (i == 1)
        {
            recursion(n, k, d + 1, ans + "+" + to_string(d), possible); 
        }
        if (i == 2)
        {
            recursion(n, k, d + 1, ans + "-" + to_string(d), possible); 
        }
    }
}

int main()
{
    int n, k;
    bool possible = false;

    cout << "Insert desired number\n";
    cin >> n;
    
    cout << "Insert amount of integers (k > 0 && k <= 14)\n";
    cin >> k;

    while (k <= 0 || k > 14)
    {
        cout << "Insert amount of integers (k > 0 && k <= 14)\n";
        cin >> k;    
    }

    recursion(n, k, 2, "1", possible);

    if (!possible)
    {
        cout << "Impossible!\n";
    }

    return 0;
}
