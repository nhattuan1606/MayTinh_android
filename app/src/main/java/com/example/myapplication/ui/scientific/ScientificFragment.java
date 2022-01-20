package com.example.myapplication.ui.scientific;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOError;
import java.util.Stack;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentScientificBinding;

public class ScientificFragment extends Fragment {

    private TextView tvSo, tvCT;
    private double[] A = new double[10];            // Lưu các số đã nhập
    private String textSo = "0", textCT = "";
    private int x;                  // Lưu số liệu về kích thước màn hình
    private int count = 0;              // Biến đếm cho mảng lưu số
    private int dem = 0;                // Dem so lan mo ngoac
    private int flag = -1;               // Bắt đầu: 1, nhập số: 0, phép toán 2 ngôi: 1, phép 1 ngồi: 2,
                                        // Mở ngoặc: 3, Đóng ngoặc: 4, Bằng: 5

    private FragmentScientificBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentScientificBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();

        x = getScreenWidth();

        tvSo = binding.tvSoSc;
        tvCT = binding.tvCTSc;

        tvCT.setMovementMethod(new ScrollingMovementMethod());
        tvCT.setHorizontallyScrolling(true);

        binding.btt0.setOnClickListener(this::PressNumber);
        binding.btt1.setOnClickListener(this::PressNumber);
        binding.btt2.setOnClickListener(this::PressNumber);
        binding.btt3.setOnClickListener(this::PressNumber);
        binding.btt4.setOnClickListener(this::PressNumber);
        binding.btt5.setOnClickListener(this::PressNumber);
        binding.btt6.setOnClickListener(this::PressNumber);
        binding.btt7.setOnClickListener(this::PressNumber);
        binding.btt8.setOnClickListener(this::PressNumber);
        binding.btt9.setOnClickListener(this::PressNumber);

        binding.bttPi.setOnClickListener(v -> {
            if (count == 10) return;
            if (flag == 2 || flag == 4) return;

            if (flag == 5) {
                tvSo.setText("0"); tvCT.setText(" ");
                textSo = "0"; textCT = "";
                count = 0; dem = 0;
            }

            if (!textSo.equals("0")) return;
            tvSo.setText("3.141 592 653 590");
            textSo = "3.141592653590";
            flag = 0;
        });
        binding.bttE.setOnClickListener(v -> {
            if (count == 10) return;
            if (flag == 2 || flag == 4) return;

            if (flag == 5) {
                tvSo.setText("0"); tvCT.setText(" ");
                textSo = "0"; textCT = "";
                count = 0; dem = 0;
            }

            if (!textSo.equals("0")) return;
            tvSo.setText("3.141 592 653 590");
            tvSo.setText("2.718 281 828 459");
            textSo = "2.718281828459";
            flag = 0;
        });

        binding.bttCham.setOnClickListener(v -> {
            if (!textSo.contains(".")) {
                textSo = textSo + ".";
                tvSo.setText(tvSo.getText().toString() + ".");
            }
        });

        binding.bttBinhPhuong.setOnClickListener(v -> {
            if (flag != 0) return;
            if (textSo.equals("0")) return;
            tvCT.setText(tvCT.getText().toString() + " sqr(" + tvSo.getText().toString() + ")");
            textCT = textCT + String.valueOf(count);
            double tmp = Double.parseDouble(textSo);
            A[count] = tmp*tmp;
            count += 1; flag = 2;
            tvSo.setText("0"); textSo = "0";
        });
        binding.bttLapPhuong.setOnClickListener(v -> {
            if (flag != 0) return;
            if (textSo.equals("0")) return;
            tvCT.setText(tvCT.getText().toString() + " cube(" + tvSo.getText().toString() + ")");
            textCT = textCT + String.valueOf(count);
            double tmp = Double.parseDouble(textSo);
            A[count] = tmp*tmp*tmp;
            count += 1; flag = 2;
            tvSo.setText("0"); textSo = "0";
        });
        binding.bttCan.setOnClickListener(v -> {
            if (flag != 0) return;
            if (textSo.equals("0")) return;
            tvCT.setText(tvCT.getText().toString() + " √(" + tvSo.getText().toString() + ")");
            textCT = textCT + String.valueOf(count);
            double tmp = Double.parseDouble(textSo);
            A[count] = Math.sqrt(tmp);
            count += 1; flag = 2;
            tvSo.setText("0"); textSo = "0";
        });
        binding.bttCan3x.setOnClickListener(v -> {
            if (flag != 0) return;
            if (textSo.equals("0")) return;
            tvCT.setText(tvCT.getText().toString() + " cuberoot(" + tvSo.getText().toString() + ")");
            textCT = textCT + String.valueOf(count);
            double tmp = Double.parseDouble(textSo);
            A[count] = Math.cbrt(tmp);
            count += 1; flag = 2;
            tvSo.setText("0"); textSo = "0";
        });
        binding.bttE10.setOnClickListener(v -> {
            if (flag != 0) return;
            if (textSo.equals("0")) return;
            tvCT.setText(tvCT.getText().toString() + " 10^(" + tvSo.getText().toString() + ")");
            textCT = textCT + String.valueOf(count);
            double tmp = Double.parseDouble(textSo);
            A[count] = Math.pow(10, tmp);
            count += 1; flag = 2;
            tvSo.setText("0"); textSo = "0";
        });
        binding.btt2muX.setOnClickListener(v -> {
            if (flag != 0) return;
            if (textSo.equals("0")) return;
            tvCT.setText(tvCT.getText().toString() + " 2^(" + tvSo.getText().toString() + ")");
            textCT = textCT + String.valueOf(count);
            double tmp = Double.parseDouble(textSo);
            A[count] = Math.pow(2, tmp);
            count += 1; flag = 2;
            tvSo.setText("0"); textSo = "0";
        });
        binding.bttlog.setOnClickListener(v -> {
            if (flag != 0) return;
            if (textSo.equals("0")) return;
            tvCT.setText(tvCT.getText().toString() + " log(" + tvSo.getText().toString() + ")");
            textCT = textCT + String.valueOf(count);
            double tmp = Double.parseDouble(textSo);
            A[count] = Math.log10(tmp);
            count += 1; flag = 2;
            tvSo.setText("0"); textSo = "0";
        });
        binding.bttln.setOnClickListener(v -> {
            if (flag != 0) return;
            if (textSo.equals("0")) return;
            tvCT.setText(tvCT.getText().toString() + " ln(" + tvSo.getText().toString() + ")");
            textCT = textCT + String.valueOf(count);
            double tmp = Double.parseDouble(textSo);
            A[count] = Math.log(tmp);
            count += 1; flag = 2;
            tvSo.setText("0"); textSo = "0";
        });
        binding.bttEmuX.setOnClickListener(v -> {
            if (flag != 0) return;
            if (textSo.equals("0")) return;
            tvCT.setText(tvCT.getText().toString() + " e^(" + tvSo.getText().toString() + ")");
            textCT = textCT + String.valueOf(count);
            double tmp = Double.parseDouble(textSo);
            A[count] = Math.exp(tmp);
            count += 1; flag = 2;
            tvSo.setText("0"); textSo = "0";
        });
        binding.bttNghichDao.setOnClickListener(v -> {
            if (flag != 0) return;
            if (textSo.equals("0")) return;
            tvCT.setText(tvCT.getText().toString() + " 1/(" + tvSo.getText().toString() + ")");
            textCT = textCT + String.valueOf(count);
            double tmp = Double.parseDouble(textSo);
            A[count] = 1/tmp;
            count += 1; flag = 2;
            tvSo.setText("0"); textSo = "0";
        });
        binding.bttAbs.setOnClickListener(v -> {
            if (flag != 0) return;
            if (textSo.equals("0")) return;
            tvCT.setText(tvCT.getText().toString() + " abs(" + tvSo.getText().toString() + ")");
            textCT = textCT + String.valueOf(count);
            double tmp = Double.parseDouble(textSo);
            if (tmp < 0) A[count] = -tmp;
            else A[count] = tmp;
            count += 1; flag = 2;
            tvSo.setText("0"); textSo = "0";
        });
        binding.bttsin.setOnClickListener(v -> {
            if (flag != 0) return;
            if (textSo.equals("0")) return;
            tvCT.setText(tvCT.getText().toString() + " sin(" + tvSo.getText().toString() + ")");
            textCT = textCT + String.valueOf(count);
            double tmp = Double.parseDouble(textSo);
            A[count] = Math.sin(tmp);
            count += 1; flag = 2;
            tvSo.setText("0"); textSo = "0";
        });
        binding.bttcos.setOnClickListener(v -> {
            if (flag != 0) return;
            if (textSo.equals("0")) return;
            tvCT.setText(tvCT.getText().toString() + " cos(" + tvSo.getText().toString() + ")");
            textCT = textCT + String.valueOf(count);
            double tmp = Double.parseDouble(textSo);
            A[count] = Math.cos(tmp);
            count += 1; flag = 2;
            tvSo.setText("0"); textSo = "0";
        });
        binding.btttan.setOnClickListener(v -> {
            if (flag != 0) return;
            if (textSo.equals("0")) return;
            tvCT.setText(tvCT.getText().toString() + " tan(" + tvSo.getText().toString() + ")");
            textCT = textCT + String.valueOf(count);
            double tmp = Double.parseDouble(textSo);
            A[count] = Math.tan(tmp);
            count += 1; flag = 2;
            tvSo.setText("0"); textSo = "0";
        });
        binding.bttcot.setOnClickListener(v -> {
            if (flag != 0) return;
            if (textSo.equals("0")) return;
            tvCT.setText(tvCT.getText().toString() + " cot(" + tvSo.getText().toString() + ")");
            textCT = textCT + String.valueOf(count);
            double tmp = Double.parseDouble(textSo);
            A[count] = Math.cos(tmp)/Math.sin(tmp);
            count += 1; flag = 2;
            tvSo.setText("0"); textSo = "0";
        });
        binding.bttNguyenDuoi.setOnClickListener(v -> {
            if (flag != 0) return;
            if (textSo.equals("0")) return;
            tvCT.setText(tvCT.getText().toString() + " floor(" + tvSo.getText().toString() + ")");
            textCT = textCT + String.valueOf(count);
            double tmp = Double.parseDouble(textSo);
            A[count] = (long) tmp;
            count += 1; flag = 2;
            tvSo.setText("0"); textSo = "0";
        });
        binding.bttNguyenDuoi.setOnClickListener(v -> {
            if (flag != 0) return;
            if (textSo.equals("0")) return;
            tvCT.setText(tvCT.getText().toString() + " ceil(" + tvSo.getText().toString() + ")");
            textCT = textCT + String.valueOf(count);
            double tmp = Double.parseDouble(textSo) + 1;
            A[count] = (long) tmp;
            count += 1; flag = 2;
            tvSo.setText("0"); textSo = "0";
        });
        binding.bttGiaiThua.setOnClickListener(v -> {
            if (flag != 0) return;
            if (textSo.equals("0")) return;
            tvCT.setText(tvCT.getText().toString() + " ceil(" + tvSo.getText().toString() + ")");
            textCT = textCT + String.valueOf(count);
            double tmp = Double.parseDouble(textSo);
            if ((tmp - (long) tmp) != 0) return;
            A[count] = tmp; tmp--;
            while (tmp != 0) {
                A[count] = A[count]*tmp;
                tmp--;
            }
            count += 1; flag = 2;
            tvSo.setText("0"); textSo = "0";
        });


        binding.bttMoNgoac.setOnClickListener(v -> {
            if (flag == 5) {                // Nếu vừa ấn = thì reset các biến về mặc định
                tvSo.setText("0"); tvCT.setText(" ");
                textCT = ""; textSo = "0";
                count = 0; dem = 0;
            } else if (flag != -1 && flag != 1 && flag != 3) return;

            tvCT.setText(tvCT.getText().toString() + "(");
            textCT = textCT + "(";

            flag = 3; dem += 1;
        });
        binding.bttDongNgoac.setOnClickListener(v -> {
            if (dem == 0) return;
            if (flag != 0 && flag != 2 && flag != 4) return;

            if (flag == 0) {
                tvCT.setText(tvCT.getText().toString() + tvSo.getText().toString() + ")");
                tvSo.setText("0");
                textCT = textCT + String.valueOf(count) + ")";
                A[count] = Double.parseDouble(textSo);
                count += 1;
                textSo = "0";
            } else {
                tvCT.setText(tvCT.getText().toString() + ")");
                textCT = textCT + ")";
            }

            flag = 4; dem -= 1;
        });
        binding.bttCong.setOnClickListener(this::PressOperator);
        binding.bttTru.setOnClickListener(this::PressOperator);
        binding.bttNhan.setOnClickListener(this::PressOperator);
        binding.bttChia.setOnClickListener(this::PressOperator);
        binding.bttXmuY.setOnClickListener(this::PressOperator);
        binding.bttBang.setOnClickListener(v -> {
            if (flag != 0 && flag != 2 && flag != 4) return;

            if (flag == 0) {
                if (dem != 0) {          // Nếu có ngoặc mở chưa đóng thì đóng ngoặc lại
                    String tmp = "";
                    for (int i = dem; i != 0; i -= 1)
                        tmp = tmp + ")";
                    tvCT.setText(tvCT.getText().toString() + tvSo.getText().toString() + tmp + " = ");
                    textCT = textCT + String.valueOf(count) + tmp;
                } else {
                    tvCT.setText(tvCT.getText().toString() + tvSo.getText().toString() + " = ");
                    textCT = textCT + String.valueOf(count);
                }
                A[count] = Double.parseDouble(textSo);
                count += 1;
            } else {
                if (dem != 0) {
                    String tmp = "";
                    for (int i = dem; i != 0; i -= 1)
                        tmp = tmp + ")";
                    tvCT.setText(tvCT.getText().toString() + tmp + " = ");
                    textCT = textCT + tmp;
                }
            }

            textSo = "0";
            flag = 5;

            try {
                //binding.tvabc.setText(textCT);
                double tmp = calc();
                if ((tmp - (long) tmp) != 0) tvSo.setText(String.valueOf(tmp));
                else tvSo.setText(formatText(String.valueOf((long) tmp)));
            } catch (IOError e) {
                tvSo.setText("Error!!!");
            }
        });


        binding.btt2nd.setOnClickListener(v -> {
            if (binding.layout2nd.getVisibility() == View.INVISIBLE)
                binding.layout2nd.setVisibility(View.VISIBLE);
            else binding.layout2nd.setVisibility(View.INVISIBLE);
        });
        binding.bttTrigo.setOnClickListener(v -> {
            if(binding.layFunc.getVisibility() == View.VISIBLE) {
                binding.layFunc.setVisibility(View.INVISIBLE);
                binding.bttFunc.setBackground(this.getResources().getDrawable(R.drawable.button_anim_3_2));
            }
            if (binding.layTrigo.getVisibility() == View.INVISIBLE) {
                binding.bttTrigo.setBackground(this.getResources().getDrawable(R.drawable.button_anim_3_1));
                binding.layTrigo.setVisibility(View.VISIBLE);
            } else {
                binding.layTrigo.setVisibility(View.INVISIBLE);
                binding.bttTrigo.setBackground(this.getResources().getDrawable(R.drawable.button_anim_3_2));
            }
        });
        binding.btt2ndplus.setOnClickListener(v -> {
            if (binding.layTrigo2.getVisibility() == View.INVISIBLE)
                binding.layTrigo2.setVisibility(View.VISIBLE);
            else binding.layTrigo2.setVisibility(View.INVISIBLE);
        });
        binding.bttFunc.setOnClickListener(v -> {
            if (binding.layTrigo.getVisibility() == View.VISIBLE) {
                binding.layTrigo.setVisibility(View.INVISIBLE);
                binding.bttTrigo.setBackground(this.getResources().getDrawable(R.drawable.button_anim_3_2));
            }
            if (binding.layFunc.getVisibility() == View.INVISIBLE) {
                binding.bttFunc.setBackground(this.getResources().getDrawable(R.drawable.button_anim_3_1));
                binding.layFunc.setVisibility(View.VISIBLE);
            } else {
                binding.layFunc.setVisibility(View.INVISIBLE);
                binding.bttFunc.setBackground(this.getResources().getDrawable(R.drawable.button_anim_3_2));
            }
        });

        binding.bttCE.setOnClickListener(v -> {
            tvSo.setText("0"); tvCT.setText(" ");
            textCT = ""; textSo = "0";
            flag = -1; count = 0; dem = 0;
        });
        binding.bttXoa.setOnClickListener(v -> {
            textSo = textSo.substring(0, textSo.length() - 1);
            if (textSo.equals("") || textSo.equals("-"))
                textSo = "0";

            tvSo.setText(formatText(textSo));
        });


        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    public void PressOperator(View view) {
        if (flag != 0 && flag != 2 && flag != 4) return;         // Nếu chưa nhập số thì không cho nhập phép toán


        String tmp;
        Button btn = (Button) view;
        if (view.getId() == R.id.bttCong) tmp = "+";
        else if (view.getId() == R.id.bttTru) tmp = "-";
        else if (view.getId() == R.id.bttNhan) tmp = "*";
        else if (view.getId() == R.id.bttChia) tmp = "/";
        else tmp = "^";

        if (flag == 0) {
            if (tmp.equals("^"))
                tvCT.setText(tvCT.getText().toString() + tvSo.getText().toString() + " ^ ");
            else tvCT.setText(tvCT.getText().toString() + tvSo.getText().toString() + " " + btn.getText().toString() + " ");
            tvSo.setText("0");
            textCT = textCT + String.valueOf(count) + tmp;
            A[count] = Double.parseDouble(textSo);
            count += 1;
            textSo = "0";
        } else {
            if (tmp.equals("^"))
                tvCT.setText(tvCT.getText().toString() + " ^ ");
            else tvCT.setText(tvCT.getText().toString() + " " + btn.getText().toString() + " ");
            textCT = textCT + tmp;
        }
        flag = 1;
    }


    public void PressNumber(View view) {
        if (count == 10) return;
        if (flag == 2 || flag == 4) return;
        Button btn = (Button) view;

        if (flag == 5) {
            tvSo.setText("0"); tvCT.setText(" ");
            textSo = "0"; textCT = "";
            count = 0; dem = 0;
        } else {
            int n = tvSo.getText().toString().length() + 1;
            if (n > 17) return;        // Nếu nhập nhiều hơn 17 kí tự thì không nhận nữa
        }

        flag = 0;
        if (textSo.equals("0"))
            textSo = "";
        textSo = textSo + btn.getText().toString();
        tvSo.setText(formatText(textSo));
    } // end PressNumber (Nhấn 1 số)


    //
    private String formatText(String s) {
        StringBuilder fs = new StringBuilder();
        if (s.contains("-")) {
            s = s.substring(1);
            fs.append("-");
        }
        int phanNguyen, j, y;
        if (s.contains("."))
            phanNguyen = s.indexOf(".");        // Nếu không phải số nguyên thì lưu vị trí phần nguyên
        else phanNguyen = s.length();
        j = phanNguyen % 3;
        y = phanNguyen/3;

        fs.append(s.substring(0, j));
        for (int i=0; i<y; i++)
            fs.append(" " + s.substring(j+3*i, j+3+3*i));

        fs.append(s.substring(phanNguyen));

        return fs.toString();
    }


    private int priority(char c) {
        if (c == '+' || c == '-') return 1;
        else if (c == '*' || c == '/') return 2;
        else if (c == '^') return 3;
        else return 0;
    }


    private String postfix(String B) {
        StringBuilder fs = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        while  (!B.isEmpty()) {
            if (B.charAt(0) >= '0' && B.charAt(0) <= '9') {         // Nếu là số thì thêm vào luôn
                fs.append(B.charAt(0));
            } else if (B.charAt(0) == '+' || B.charAt(0) == '-') {      // Nếu là dấu +, - thì lấy khỏi stack
                while (!stack.empty() && priority(stack.peek()) > 0) {      // đến khi gặp '(' tức độ ưu tiên = 0
                    fs.append(stack.peek());
                    stack.pop();
                }
                stack.push(B.charAt(0));                // Thêm kí tự mới vào stack
            } else if (B.charAt(0) == '*' || B.charAt(0) == '/') {      // Nếu là dấu *, / thì lấy khỏi stack
                while (!stack.empty() && priority(stack.peek()) > 1) {      // tất cả dấu *, /, ^ tức dộ ưu tiên = 2, 3
                    fs.append(stack.peek());
                    stack.pop();
                }
                stack.push(B.charAt(0));                // Thêm kí tự mới vào stack
            } else if (B.charAt(0) == '(' || B.charAt(0) == '^') {      // Nếu gặp ( hoặc ^ thì đưa vào stack luôn
                stack.push(B.charAt(0));
            } else {                                    // Nếu gặp ) thì đưa ra khỏi stack cho tới khi gặp (
                while (stack.peek() != '(') {
                    fs.append(stack.peek());
                    stack.pop();
                }
                stack.pop();
            }

            B = B.substring(1);             // Loại bỏ kí tự đầu tiên của chuỗi
        } // end while

        while (!stack.empty()) {
            fs.append(stack.peek());
            stack.pop();
        }

        return fs.toString();
    } // postfix


    private double tinh(double a, double b, char c) {
        if (c == '+') return b+a;
        else if (c == '-') return b-a;
        else if (c == '*') return b*a;
        else if (c == '/') return b/a;
        else return Math.pow(b, a);
    }


    private double calc() {
        Stack<Character> stack = new Stack<>();
        String B = postfix(textCT);
        while (!B.isEmpty()) {
            if (B.charAt(0) >= '0' && B.charAt(0) <= '9') {
                stack.push(B.charAt(0));
                System.out.println("push " + A[Character.getNumericValue(B.charAt(0))]);
            } else {
                int i = Character.getNumericValue(stack.peek());
                stack.pop();
                double a = A[i];
                System.out.println("pop" + a);
                i = Character.getNumericValue(stack.peek());
                stack.pop();
                double b = A[i];
                System.out.println(b);
                System.out.println("pop" + b);
                A[i] = tinh(a, b, B.charAt(0));
                System.out.println("push 1 " + i + "    " + A[i]);
                stack.push((char) (i+48));
                System.out.println("push 2 " + stack.peek() + "    " + A[Character.getNumericValue(stack.peek())]);
            }

            B = B.substring(1);
        }
        return A[Character.getNumericValue(stack.peek())];
    } // end calc


    // Lấy chiều rộng màn hình
    public int getScreenWidth() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        return displaymetrics.widthPixels;
    } // end getScreenWidth

}