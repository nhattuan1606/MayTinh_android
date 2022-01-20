package com.example.myapplication.ui.standard;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentStandardBinding;


public class StandardFragment extends Fragment {

    //private StandardViewModel standardViewModel;
    private FragmentStandardBinding binding;

    private TextView tvSo, tvCT;        // tvSo: textview hiển thị số vừa nhập vào;
                                        // tvCT: textview hiển thị số trước đó và dấu phép tính
    private byte flag = 0;              // flag đánh dấu xem dấu được nhập vào là cộng trừ nhân chia
    private String textSo = "0";    // text trước khi đc định dạng để hiển thị
    private double num1, num2;
    private int x;                  // Lưu số liệu về kích thước màn hình


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        standardViewModel =
//                new ViewModelProvider(this).get(StandardViewModel.class);

        binding = FragmentStandardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


//        MainActivity m = new MainActivity();
        x = getScreenWidth();


        tvSo = binding.textViewSo;
        tvCT = binding.textViewCongThuc;

        //tvSo.setHeight(x/4);
        tvSo.setTextSize(x/12);
        tvCT.setTextSize(x/32);

        binding.button0.setOnClickListener(this::PressNumber);
        binding.button1.setOnClickListener(this::PressNumber);
        binding.button2.setOnClickListener(this::PressNumber);
        binding.button3.setOnClickListener(this::PressNumber);
        binding.button4.setOnClickListener(this::PressNumber);
        binding.button5.setOnClickListener(this::PressNumber);
        binding.button6.setOnClickListener(this::PressNumber);
        binding.button7.setOnClickListener(this::PressNumber);
        binding.button8.setOnClickListener(this::PressNumber);
        binding.button9.setOnClickListener(this::PressNumber);

        binding.buttonCE.setOnClickListener(v -> {
            tvSo.setText("0");
            textSo = "0";
            tvSo.setTextSize(x/12);
        });
        binding.buttonC.setOnClickListener(v -> {
            tvSo.setText("0");
            textSo = "0";
            tvCT.setText(" ");
            num1 = 0;
            num2 = 0;
            flag = 0;
            tvSo.setTextSize(x/12);
        });
        binding.buttonXoa.setOnClickListener(v -> {
            textSo = textSo.substring(0, textSo.length() - 1);
            if (textSo.equals("") || textSo.equals("-"))
                textSo = "0";

            tvSo.setText(formatText(textSo));
        });

        binding.buttonCong.setOnClickListener(this::PressOperation);
        binding.buttonTru.setOnClickListener(this::PressOperation);
        binding.buttonNhan.setOnClickListener(this::PressOperation);
        binding.buttonChia.setOnClickListener(this::PressOperation);

        binding.buttonBang.setOnClickListener(this::PressEqualBtn);

        binding.buttonDauAm.setOnClickListener(v -> {
            if (textSo.equals("0"))
                return;
            if (textSo.contains("-"))
                textSo = textSo.substring(1);
            else textSo = "-" + textSo;
            tvSo.setText(formatText(textSo));
        });
        binding.buttonCham.setOnClickListener(v -> {
            if (!textSo.contains(".")) {
                textSo = textSo + ".";
                tvSo.setText(tvSo.getText().toString() + ".");
            }
        });
        binding.buttonPhanTram.setOnClickListener(v -> {
            if (flag == 0) {                        // Nếu chưa có phép toàn nào được bấm trước đó thay số ở ô text bên dưới lên trên
                tvCT.setText(tvSo.getText().toString() + "% ");
                num1 = Double.parseDouble(textSo)/100;
                if ( (num1 - (long) num1) == 0)
                    textSo = String.valueOf((long) num1);
                else textSo = String.valueOf(num1);
                tvSo.setText(formatText(textSo));
            } else {                            // Nếu có phép toán nào được bấm trước đó thì thực hiện phép toán đó rồi thêm giá trị vào ô text bên trên
                tvCT.setText(tvCT.getText().toString() + tvSo.getText() + "% ");
                num2 = Double.parseDouble(textSo)/100;
                num1 = Calculate(num1, num2);
                if ( (num1 - (long) num1) == 0)
                    textSo = String.valueOf((long) num1);            // Nếu là số nguyên thì k in số .0
                else textSo = String.valueOf(num1);
                tvSo.setText(formatText(textSo));
                flag = 0;
            }
        });
        binding.buttonBinhPhuong.setOnClickListener(v -> {
            if (flag == 0) {                        // Nếu chưa có phép toàn nào được bấm trước đó thay số ở ô text bên dưới lên trên
                tvCT.setText(tvSo.getText().toString() + "² ");
                num1 = Double.parseDouble(textSo);
                num1 = num1*num1;
                if ( (num1 - (long) num1) == 0)
                    textSo = String.valueOf((long) num1);
                else textSo = String.valueOf(num1);
                tvSo.setText(formatText(textSo));
            } else {                            // Nếu có phép toán nào được bấm trước đó thì thực hiện phép toán đó rồi thêm giá trị vào ô text bên trên
                tvCT.setText(tvCT.getText().toString() + tvSo.getText() + "² ");
                num2 = Double.parseDouble(textSo);
                num2 = num2*num2;
                num1 = Calculate(num1, num2);
                if ( (num1 - (long) num1) == 0)
                    textSo = String.valueOf((long) num1);            // Nếu là số nguyên thì k in số .0
                else textSo = String.valueOf(num1);
                tvSo.setText(formatText(textSo));
                flag = 0;
            }
        });
        binding.buttonCan.setOnClickListener(v -> {
            if (flag == 0) {                        // Nếu chưa có phép toàn nào được bấm trước đó thay số ở ô text bên dưới lên trên
                tvCT.setText("√(" + tvSo.getText().toString() + ") ");
                num1 = Math.sqrt(Double.parseDouble(textSo));
                if ( (num1 - (long) num1) == 0)
                    textSo = String.valueOf((long) num1);
                else textSo = String.valueOf(num1);
                tvSo.setText(formatText(textSo));
            } else {                            // Nếu có phép toán nào được bấm trước đó thì thực hiện phép toán đó rồi thêm giá trị vào ô text bên trên
                tvCT.setText(tvCT.getText().toString() + "√(" + tvSo.getText() + ") ");
                num2 = Math.sqrt(Double.parseDouble(textSo));
                num1 = Calculate(num1, num2);
                if ( (num1 - (long) num1) == 0)
                    textSo = String.valueOf((long) num1);            // Nếu là số nguyên thì k in số .0
                else textSo = String.valueOf(num1);
                tvSo.setText(formatText(textSo));
                flag = 0;
            }
        });
        binding.buttonNghichDao.setOnClickListener(v -> {
            if (flag == 0) {                        // Nếu chưa có phép toàn nào được bấm trước đó thay số ở ô text bên dưới lên trên
                tvCT.setText("1/(" + tvSo.getText().toString() + ") ");
                num1 = 1/Double.parseDouble(textSo);
                if ( (num1 - (long) num1) == 0)
                    textSo = String.valueOf((long) num1);
                else textSo = String.valueOf(num1);
                tvSo.setText(formatText(textSo));
            } else {                            // Nếu có phép toán nào được bấm trước đó thì thực hiện phép toán đó rồi thêm giá trị vào ô text bên trên
                tvCT.setText(tvCT.getText().toString() + "1/(" + tvSo.getText() + ") ");
                num2 = 1/Double.parseDouble(textSo);
                num1 = Calculate(num1, num2);
                if ( (num1 - (long) num1) == 0)
                    textSo = String.valueOf((long) num1);            // Nếu là số nguyên thì k in số .0
                else textSo = String.valueOf(num1);
                tvSo.setText(formatText(textSo));
                flag = 0;
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    public void PressNumber(View view) {
        Button btn = (Button) view;
        int n = tvSo.getText().toString().length() + 1;
//        if (so.equals("0"))
//            tvSo.setText("");

        if (textSo.equals("na")) {
            textSo = "0";
        } else if (n > 17)
            return;        // Nếu nhập nhiều hơn 17 số thì không nhận nữa

        if (textSo.equals("0"))
            textSo = "";
        //String b = (new StringBuilder()).append(tvSo.getText()).append(a.getText()).toString();
        textSo = textSo + btn.getText().toString();
//        textSo = tvSo.getText().toString() + btn.getText().toString();
        tvSo.setText(formatText(textSo));
    } // end PressNumber (Nhấn 1 số)


    public void PressOperation(View view) {
        Button btn = (Button) view;

        // Nếu chưa nhập số tiếp theo vào thì không chấp nhận nút vừa ấn
        if (textSo.equals("0") || textSo.equals("na"))
            return;

        if (flag == 0) {                        // Nếu chưa có phép toàn nào được bấm trước đó thay số ở ô text bên dưới lên trên
//            String a = (new StringBuilder()).append(tvSo.getText()).append(btn.getText()).toString();
//            tvCT.setText(tvSo.getText().toString()) + " " + btn.getText().toString() + " ");
            num1 = Double.parseDouble(textSo);
        } else {                            // Nếu có phép toán nào được bấm trước đó thì thực hiện phép toán đó rồi thêm giá trị vào ô text bên trên
            num2 = Double.parseDouble(textSo);
            num1 = Calculate(num1, num2);
            if ( (num1 - (long) num1) == 0)
                tvSo.setText(formatText(String.valueOf((long) num1)));
//                tvCT.setText(formatText(String.valueOf((long) num1)) + " " + btn.getText().toString() + " ");            // Nếu là số nguyên thì k in số .0
            else tvSo.setText(formatText(String.valueOf(num1)));
//                tvCT.setText(formatText(String.valueOf(num1)) + " " + btn.getText().toString() + " ");
        }

        tvCT.setText(tvSo.getText() + " " + btn.getText() + " ");
        textSo = "na";

        // Đặt giá trị cho flag ứng với dấu vừa bấm
        if (view.getId() == R.id.buttonCong)
            flag = 1;
        else if (view.getId() == R.id.buttonTru)
            flag = 2;
        else if (view.getId() == R.id.buttonNhan)
            flag = 3;
        else if (view.getId() == R.id.buttonChia)
            flag = 4;
    } // end Press Operation (Nhấn 1 phép toán)


    public void PressEqualBtn(View view) {
        // Nếu chưa nhập số thì không chấp nhận thực hiện ấn bằng
        if (textSo.equals("0") || flag == 0)
            return;

        tvCT.setText(tvCT.getText().toString() + tvSo.getText() + " = ");
        num2 = Double.parseDouble(textSo);
        num1 = Calculate(num1, num2);
        if ((num1 - (long) num1) == 0)
            textSo = String.valueOf((long) num1);
//            tvSo.setText(String.valueOf((int) num1));
        else textSo = String.valueOf(num1);
//        else tvSo.setText(String.valueOf(num1));

        tvSo.setText(formatText(textSo));

        flag = 0;
    } // PressEqualBtn


    private double Calculate(double n1, double n2) {
        if (flag == 1)
            return n1 + n2;
        if (flag == 2)
            return n1 - n2;
        if (flag == 3)
            return n1*n2;
        return n1/n2;
    } // end caculate


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

        // Chỉnh kích thước số
        int n = fs.toString().length();
        if (n < 10)
            tvSo.setTextSize(x/12);
        else if (n < 14)
            tvSo.setTextSize(x/20);
        else tvSo.setTextSize(x/24);
//        else if (n <18)
//            tvSo.setTextSize(x/20);


        return fs.toString();
    }


    // Lấy chiều rộng màn hình
    public int getScreenWidth() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        return displaymetrics.widthPixels;
    } // end getScreenWidth

}