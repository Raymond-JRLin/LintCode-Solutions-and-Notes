// 208. Assignment Operator Overloading (C++ Only)
// 中文English
// Implement an assignment operator overloading method.
//
// Make sure that:
//
// The new data can be copied correctly
// The old data can be deleted / free correctly.
// We can assign like A = B = C
// Example
// If we assign like A = B, the data in A should be deleted, and A can have a copy of data from B.
// If we assign like A = B = C, both A and B should have a copy of data from C.
//
// Challenge
// Consider about the safety issue if you can and make sure you released the old data.
//
// Clarification
// This problem is for C++ only as Java and Python don't have overloading for assignment operator.
//


class Solution {
public:
    char *m_pData;
    Solution() {
        this->m_pData = NULL;
    }
    Solution(char *pData) {
        if (pData) {
            m_pData = new char[strlen(pData) + 1];
            strcpy(m_pData, pData);
        } else {
            m_pData = NULL;
        }
    }

    // Implement an assignment operator
    Solution operator=(const Solution &object) {
        // write your code here
        if (this == &object) {
            return *this;
        }

        if (object.m_pData) {
            char *temp = m_pData;
            try {
                m_pData = new char[strlen(object.m_pData)+1];
                strcpy(m_pData, object.m_pData);
                if (temp)
                    delete[] temp;
            } catch (bad_alloc& e) {
                m_pData = temp;
            }
        } else {
            m_pData = NULL;
        }
        return *this;

    }
};
