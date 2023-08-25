package com.company;

public class Functions {

    public double function_one (int bit, double[][] startArray, int index) // Sphere
    {
        double result = 0;
        for (int i = 0; i < bit; i++)
        {
            result += startArray[index][i] * startArray[index][i];
        }
        return result;
    }

    public double function_two(int bit, double[][] startArray, int index){ // Ellipsoidal
        double result = 0;
        for (int i = 0; i < bit; i++)
        {
            result += Math.pow(10.0,6.0*i/(bit-1))*startArray[index][i] * startArray[index][i];
        }
        return result;
    }
    public double function_three(int bit, double[][] startArray, int index){ // sum of different power
        double sum = 0;
        double xi = startArray[index][0];
        double abs = Math.abs(xi);
        double newv = Math.pow(abs,(index+1));
        sum += newv;
        return sum;
    }
    public double function_four(int bit, double[][] startArray, int index){ // Zakharov
        double sum1 = 0.0;
        double sum2 = 0.0;
        double result = 0;

        for (int i=0; i<bit; i++){
            double xi = startArray[index][i];
            sum1 = sum1 + Math.pow(xi,2);
            sum2 = sum2 + 0.5*(i+1)*xi;
        }
        result = sum1 + Math.pow(sum2,2) + Math.pow(sum2,4);
        return result;
    }
    public double function_five(int bit, double[][] startArray, int index, int population){ // Levy
        double result = 0;
        double[]w = new double[bit];

        double sum1= 0.0;
        for (int i=0; i<bit; i++){
            w[i] = 1.0 + (startArray[index][i] - 1.0)/4.0;
        }

        double term1 = Math.pow((Math.sin(Math.PI*w[0])),2);
        double term3 = Math.pow((w[bit-1]-1),2) * (1+Math.pow((Math.sin(2*Math.PI*w[bit-1])),2));

        double sum = 0.0;
        for (int i=0; i<bit-1; i++) {
            double wi = w[i]; // index
            double newv = Math.pow((wi - 1), 2) * (1 + 10 * Math.pow((Math.sin(Math.PI * wi + 1)), 2));
            sum = sum + newv;
        }
        result = term1 + sum + term3;
        return result;
    }
    public double function_six(int bit, double[][] startArray, int index, int indexOld){ // Dixon and price
        double result = 0;
        double x1 = startArray[0][0];;
        double term1 = Math.pow((x1-1),2);
        double xold;
        double newv;

        double sum = 0;
        for (int i=1; i<bit; i++) {
            double xi = startArray[index][i];

            if (indexOld < 1) {
                newv = index * Math.pow((Math.pow(2 * xi, 2)), 2);
            } else {
                xold = startArray[indexOld][i];
                newv = i * Math.pow((Math.pow(2 * xi, 2) - xold), 2);
            }
            sum = sum + newv;
        }

        result = term1 + sum;
        return result;
    }
    public double function_seven(int bit, double[][] startArray, int index){ // Bent Cigar
        double result = startArray[index][0] * startArray[index][0];
        for (int i=1; i<bit; i++) {
            result += startArray[index][i] * startArray[index][i];
        }
        result = result * Math.pow(10, 6);
        return result;
    }
    public double function_eight(int bit, double[][] startArray, int index){ // Discus
        double result = 0;
        result = Math.pow(10.0,6.0)*startArray[index][0]*startArray[index][0];
        for (int i=1; i<bit;i++){
            result += startArray[index][i]*startArray[index][i];
        }
        return result;
    }
    public double function_nine(int bit, double[][] startArray, int index){ // Different Powers
        double result = 0;
        result = Math.pow(Math.pow(Math.abs(startArray[index][0]),2+4*index/(bit-1)), 0.5);
        return result;
    }
    public double function_ten(int bit, double[][] startArray, int index, int indexPlus, int population){ // Rosenbrock
        double result = 0;
        double tmp1;
        double tmp2;
        if (index == population-1){
            tmp1=startArray[index][0]*startArray[index][0];
            tmp2=startArray[index][0]-1.0;
        }
        else{
            tmp1=(startArray[index][0]*startArray[index][0])-startArray[indexPlus][0];
            tmp2=startArray[index][0]-1.0;}
        result = 100.0*tmp1*tmp1 +tmp2*tmp2;
        return result;
    }
    public double function_eleven(int bit, double[][] startArray, int index){ // Happy Cat
        double result = 0;
        double alpha = 1.0/8.0;
        double r2 = 0;
        double sum_z = 0;
        r2 = startArray[index][0]*startArray[index][0];
        sum_z = startArray[index][0];
        result = Math.pow(Math.abs(r2-bit),2*alpha) + (0.5*r2 + sum_z)/bit + 0.5;
        return result;
    }
}
