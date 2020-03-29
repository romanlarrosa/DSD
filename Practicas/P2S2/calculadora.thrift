service Calculadora{
    void ping(),
    double suma(1:double num1, 2:double num2),
    double sub(1:double num1, 2:double num2),
    double mult(1:double num1, 2:double num2),
    double div(1:double num1, 2:double num2),

    list<double> sumVec(1:list<double> vec1, 2:list<double> vec2),
    list<double> subVec(1:list<double> vec1, 2:list<double> vec2),
    double escVec(1:list<double> vec1, 2:list<double> vec2),

}