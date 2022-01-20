package com.example.myapplication.ui.programmer;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.content.res.Resources;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentProgrammerBinding;

public class ProgrammerFragment extends Fragment {

//    private ProgrammerViewModel programmerViewModel;
    String[] phepToan = {"", "+", "−", "×", "÷"};
    private FragmentProgrammerBinding binding;
    private TextView tvSo, tvCT, tvHEX, tvDEC, tvOCT, tvBIN; // tvTest;
    private Button btnCE;
    private int x;
    private long num, num1, num2;
    private byte flag_base = 2;         // flag_base: =1 là HEX, =2 là DEC, =3 là OCT, =4 là BIN
    private byte flag = 0;
    private boolean flagCE = false;         // flagCE = true thì dang là nút CE, = false thì đang là nút C
    private boolean flagDau = true;     // flagDau để đánh dấu xem số ở textSo là kết quả hay số đc nhập
                                        // flagDau = true tức là số vừa nhập vào, = false là kết quả
    String textSo = "0";


    @SuppressLint("UseCompatLoadingForDrawables")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        programmerViewModel =
//                new ViewModelProvider(this).get(ProgrammerViewModel.class);

        binding = FragmentProgrammerBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();

        x = getScreenWidth();


        tvSo = binding.tvSoPrg;
        tvCT = binding.tvCTPrg;
        tvHEX = binding.tvHEX;
        tvDEC = binding.tvDEC;
        tvOCT = binding.tvOCT;
        tvBIN = binding.tvBIN;
        btnCE = binding.btnCE;

//        tvTest = binding.tvTest;


        tvSo.setTextSize(x/30);
        tvCT.setTextSize(x/32);
        binding.hex.setTextSize(x/64);
        binding.dec.setTextSize(x/64);
        binding.oct.setTextSize(x/64);
        binding.bin.setTextSize(x/64);
        binding.tvHEX.setTextSize(x/50);
        binding.tvDEC.setTextSize(x/50);
        binding.tvOCT.setTextSize(x/50);
        binding.tvBIN.setTextSize(x/50);


        tvCT.setMovementMethod(new ScrollingMovementMethod());
        tvCT.setHorizontallyScrolling(true);


        binding.layHEX.setOnClickListener(v -> {
            flag_base = 1;
            tvSo.setTextSize(x/25);

            if (flag != 0) {
                tvCT.setText(formatText(Long.toHexString(num1), true) + " " + phepToan[flag] + " ");
            } else tvCT.setText(" ");

            textSo = Long.toHexString(num);
            tvSo.setText(formatText(textSo, true));
            ena_hex();
            ena_dec();
            ena_oct();
            binding.lnLayHEX.setBackground(this.getResources().getDrawable(R.drawable.prg_bgr));
            binding.lnLayBIN.setBackground(this.getResources().getDrawable(R.drawable.prg_bgr_false));
            binding.lnLayOCT.setBackground(this.getResources().getDrawable(R.drawable.prg_bgr_false));
            binding.lnLayDEC.setBackground(this.getResources().getDrawable(R.drawable.prg_bgr_false));
        });
        binding.layDEC.setOnClickListener(v -> {
            flag_base = 2;
            tvSo.setTextSize(x/30);

            if (flag != 0) {
                tvCT.setText(formatText(Long.toString(num1), false) + " " + phepToan[flag] + " ");
            } else tvCT.setText(" ");

            textSo = Long.toString(num);
            tvSo.setText(formatText(textSo, false));
            dis_hex();
            ena_dec();
            ena_oct();
            binding.lnLayDEC.setBackground(this.getResources().getDrawable(R.drawable.prg_bgr));
            binding.lnLayHEX.setBackground(this.getResources().getDrawable(R.drawable.prg_bgr_false));
            binding.lnLayOCT.setBackground(this.getResources().getDrawable(R.drawable.prg_bgr_false));
            binding.lnLayBIN.setBackground(this.getResources().getDrawable(R.drawable.prg_bgr_false));
        });
        binding.layOCT.setOnClickListener(v -> {
            flag_base = 3;
            tvSo.setTextSize(x/32);

            if (flag != 0) {
                tvCT.setText(formatText(Long.toOctalString(num1), false) + " " + phepToan[flag] + " ");
            } else tvCT.setText(" ");

            textSo = Long.toOctalString(num);
            tvSo.setText(formatText(textSo, false));
            dis_hex();
            dis_dec();
            ena_oct();
            binding.lnLayOCT.setBackground(this.getResources().getDrawable(R.drawable.prg_bgr));
            binding.lnLayHEX.setBackground(this.getResources().getDrawable(R.drawable.prg_bgr_false));
            binding.lnLayDEC.setBackground(this.getResources().getDrawable(R.drawable.prg_bgr_false));
            binding.lnLayBIN.setBackground(this.getResources().getDrawable(R.drawable.prg_bgr_false));
        });
        binding.layBIN.setOnClickListener(v -> {
            flag_base = 4;
            tvSo.setTextSize(x/40);

            if (flag != 0) {
                tvCT.setText(formatText(Long.toBinaryString(num1), true) + " " + phepToan[flag] + " ");
            } else tvCT.setText(" ");

            textSo = Long.toBinaryString(num);
            tvSo.setText(formatText(textSo, true));
            dis_hex();
            dis_dec();
            dis_oct();
            binding.lnLayBIN.setBackground(this.getResources().getDrawable(R.drawable.prg_bgr));
            binding.lnLayHEX.setBackground(this.getResources().getDrawable(R.drawable.prg_bgr_false));
            binding.lnLayDEC.setBackground(this.getResources().getDrawable(R.drawable.prg_bgr_false));
            binding.lnLayOCT.setBackground(this.getResources().getDrawable(R.drawable.prg_bgr_false));
        });


        binding.btn0.setOnClickListener(this::PressNumber);
        binding.btn1.setOnClickListener(this::PressNumber);
        binding.btn2.setOnClickListener(this::PressNumber);
        binding.btn3.setOnClickListener(this::PressNumber);
        binding.btn4.setOnClickListener(this::PressNumber);
        binding.btn5.setOnClickListener(this::PressNumber);
        binding.btn6.setOnClickListener(this::PressNumber);
        binding.btn7.setOnClickListener(this::PressNumber);
        binding.btn8.setOnClickListener(this::PressNumber);
        binding.btn9.setOnClickListener(this::PressNumber);
        binding.btnA.setOnClickListener(this::PressNumber);
        binding.btnB.setOnClickListener(this::PressNumber);
        binding.btnC.setOnClickListener(this::PressNumber);
        binding.btnD.setOnClickListener(this::PressNumber);
        binding.btnE.setOnClickListener(this::PressNumber);
        binding.btnF.setOnClickListener(this::PressNumber);


        binding.btnCong.setOnClickListener(this::PressOperation);
        binding.btnTru.setOnClickListener(this::PressOperation);
        binding.btnNhan.setOnClickListener(this::PressOperation);
        binding.btnChia.setOnClickListener(this::PressOperation);
        binding.btnDichPhai.setOnClickListener(this::PressOperation);
        binding.btnDichTrai.setOnClickListener(this::PressOperation);
        binding.btnPhanTram.setOnClickListener(this::PressOperation);
        binding.btnAND.setOnClickListener(this::PressOperation);
        binding.btnOR.setOnClickListener(this::PressOperation);
        binding.btnNOT.setOnClickListener(this::PressOperation);
        binding.btnNAND.setOnClickListener(this::PressOperation);
        binding.btnNOR.setOnClickListener(this::PressOperation);
        binding.btnXOR.setOnClickListener(this::PressOperation);

        binding.btnBang.setOnClickListener(this::PressEqualBtn);

        binding.btnCE.setOnClickListener(v -> {
            if (flagCE) {
                tvHEX.setText("0");
                tvDEC.setText("0");
                tvOCT.setText("0");
                tvBIN.setText("0");
                tvSo.setText("0");
                textSo = "0";
                num = 0;
                btnCE.setText("C");
                flagCE = false;
            } else {
                tvCT.setText(" ");
                num1 = 0;
                flag = 0;
            }
        });
        binding.btnXoa.setOnClickListener(v -> {
            textSo = textSo.substring(0, textSo.length() - 1);
            if (textSo.equals("") || textSo.equals("-"))
                textSo = "0";

            if (flag_base == 2) num = Long.parseLong(textSo);
            else if (flag_base == 1) num = hex_to_dec(textSo);
            else if (flag_base == 4) num = bin_to_dec(textSo);
            else num = oct_to_dec(textSo);

            if (flag_base == 1 || flag_base == 4)
                tvSo.setText(formatText(textSo, true));
            else tvSo.setText(formatText(textSo, false));
            tvHEX.setText(formatText(Long.toHexString(num), true));
            tvDEC.setText(formatText(Long.toString(num), false));
            tvOCT.setText(formatText(Long.toOctalString(num), false));
            tvBIN.setText(formatText(Long.toBinaryString(num), true));
        });

        binding.btnDauAm.setOnClickListener(v -> {
            if (textSo.equals("0"))
                return;
            if (textSo.contains("-"))
                textSo = textSo.substring(1);
            else textSo = "-" + textSo;

            if (flag_base == 2) num = Long.parseLong(textSo);
            else if (flag_base == 1) num = hex_to_dec(textSo);
            else if (flag_base == 4) num = bin_to_dec(textSo);
            else num = oct_to_dec(textSo);

            if (flag_base == 1 || flag_base == 4)
                tvSo.setText(formatText(textSo, true));
            else tvSo.setText(formatText(textSo, false));
            tvHEX.setText(formatText(Long.toHexString(num), true));
            tvDEC.setText(formatText(Long.toString(num), false));
            tvOCT.setText(formatText(Long.toOctalString(num), false));
            tvBIN.setText(formatText(Long.toBinaryString(num), true));
        });

        binding.btnBitwise.setOnClickListener(v -> {
            if (binding.layoutPlus.getVisibility() == View.INVISIBLE) {
                binding.btnBitwise.setBackground(this.getResources().getDrawable(R.drawable.button_anim_3_1));
                binding.layoutPlus.setVisibility(View.VISIBLE);
            } else {
                binding.layoutPlus.setVisibility(View.INVISIBLE);
                binding.btnBitwise.setBackground(this.getResources().getDrawable(R.drawable.button_anim_3_2));
            }

        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////
    // Hàm nhấn 1 số
    public void PressNumber(View view) {
        btnCE.setText("CE");
        flagCE = true;
        Button btn = (Button) view;
        int n;
        if (tvSo.getText().toString().contains("-"))
            n = tvSo.getText().toString().length() - 1;
        else n = tvSo.getText().toString().length();

        if (flagDau) {
            if (flag_base == 1) {
                if (n > 18) return;        // Ở HEX nếu nhập nhiều hơn 12 chữ số (14 kí tự trong text) thì không nhận nữa
            } else if (flag_base == 2) {
                if (n > 22) return;        // ở DEC nếu nhập nhiều hơn 14 chữ số (18 kí tự trong text) thì không nhận nữa
            } else if (flag_base == 3) {
                if (n > 26) return;        // ở OCT nếu nhập nhiều hơn 16 chữ số (21 kí tự trong text) thì không nhận nữa
            } else if (n > 78) return;     // ở BIN nếu nhập nhiều hơn 48 chữ số (59 kí tự trong text) thì không nhận nữa
        }

        if (textSo.equals("0") || !flagDau) {
            textSo = "";
            flagDau = true;
        }
        textSo = textSo + btn.getText().toString();

        if (flag_base == 2) num = Long.parseLong(textSo);
        else if (flag_base == 1) num = hex_to_dec(textSo);
        else if (flag_base == 4) num = bin_to_dec(textSo);
        else num = oct_to_dec(textSo);

        if (flag_base == 1 || flag_base == 4)
            tvSo.setText(formatText(textSo, true));
        else tvSo.setText(formatText(textSo, false));
        tvHEX.setText(formatText(Long.toHexString(num), true));
        tvDEC.setText(formatText(Long.toString(num), false));
        tvOCT.setText(formatText(Long.toOctalString(num), false));
        tvBIN.setText(formatText(Long.toBinaryString(num), true));
    } // end PressNumber (Nhấn 1 số)



    ///////////////////////////////////////////////////////////////////////////////////////////////
    // Hàm bấm 1 phép toán
    public void PressOperation(View view) {
        Button btn = (Button) view;

        // Nếu chưa nhập số tiếp theo vào thì không chấp nhận nút vừa ấn
        if (textSo.equals("0") || !flagDau) {
//            tvTest.setText("abc");
            return;
        }


        if (flag == 0) {                        // Nếu chưa có phép toàn nào được bấm trước đó thay số ở ô text bên dưới lên trên
//            String a = (new StringBuilder()).append(tvSo.getText()).append(btn.getText()).toString();
            tvCT.setText(tvSo.getText() + " " + btn.getText() + " ");
            num1 = num;
        } else {                            // Nếu có phép toán nào được bấm trước đó thì thực hiện phép toán đó rồi thêm giá trị vào ô text bên trên
            num1 = Calculate(num1, num);
            String text;
            if (flag_base == 2) text = Long.toString(num1);
            else if (flag_base == 1) text = Long.toHexString(num1);
            else if (flag_base == 4) text = Long.toBinaryString(num1);
            else text = Long.toOctalString(num1);
            if (flag_base == 1 || flag_base == 4)
                tvCT.setText(formatText(text, true) + " " + btn.getText() + " ");
            else tvCT.setText(formatText(text, false) + " " + btn.getText() + " ");
//            + tvSo.getText() + " " + btn.getText().toString() + " "
        }

        flagDau = false;

//        if (flag_base == 2) num = Long.parseLong(textSo);
//        else if (flag_base == 1) num = hex_to_dec(textSo);
//        else if (flag_base == 4) num = bin_to_dec(textSo);
//        else num = oct_to_dec(textSo);

        num = num1;
        if (flag_base == 2) textSo = Long.toString(num);
        else if (flag_base == 1) textSo = Long.toHexString(num);
        else if (flag_base == 4) textSo = Long.toBinaryString(num);
        else textSo = Long.toOctalString(num);

        if (flag_base == 1 || flag_base == 4)
            tvSo.setText(formatText(textSo, true));
        else tvSo.setText(formatText(textSo, false));
        tvHEX.setText(formatText(Long.toHexString(num), true));
        tvDEC.setText(formatText(Long.toString(num), false));
        tvOCT.setText(formatText(Long.toOctalString(num), false));
        tvBIN.setText(formatText(Long.toBinaryString(num), true));


        // Đặt giá trị cho flag ứng với dấu vừa bấm
        if (view.getId() == R.id.btnCong)
            flag = 1;
        else if (view.getId() == R.id.btnTru)
            flag = 2;
        else if (view.getId() == R.id.btnNhan)
            flag = 3;
        else if (view.getId() == R.id.btnChia)
            flag = 4;
        else if (view.getId() == R.id.btnDichTrai)
            flag = 5;
        else if (view.getId() == R.id.btnDichPhai)
            flag = 6;
        else if (view.getId() == R.id.btnPhanTram)
            flag = 7;
        else if (view.getId() == R.id.btnAND)
            flag = 8;
        else if (view.getId() == R.id.btnOR)
            flag = 9;
        else if (view.getId() == R.id.btnNAND)
            flag = 10;
        else if (view.getId() == R.id.btnNOR)
            flag = 11;
        else if (view.getId() == R.id.btnXOR)
            flag = 12;

    } // end Press Operation (Nhấn 1 phép toán)



    public void PressEqualBtn(View view) {
        // Nếu chưa nhập số thì không chấp nhận thực hiện ấn bằng
        if (textSo.equals("0") || flag == 0)
            return;

        tvCT.setText(tvCT.getText().toString() + tvSo.getText() + " = ");
        num1 = Calculate(num1, num);

        num = num1;
        if (flag_base == 2) textSo = Long.toString(num);
        else if (flag_base == 1) textSo = Long.toHexString(num);
        else if (flag_base == 4) textSo = Long.toBinaryString(num);
        else textSo = Long.toOctalString(num);

        if (flag_base == 1 || flag_base == 4)
            tvSo.setText(formatText(textSo, true));
        else tvSo.setText(formatText(textSo, false));
        tvHEX.setText(formatText(Long.toHexString(num), true));
        tvDEC.setText(formatText(Long.toString(num), false));
        tvOCT.setText(formatText(Long.toOctalString(num), false));
        tvBIN.setText(formatText(Long.toBinaryString(num), true));

        flag = 0;
    } // PressEqualBtn


    // Tính toán các phép tính
    private long Calculate(long n1, long n2) {
        if (flag == 1)
            return n1 + n2;
        else if (flag == 2)
            return n1 - n2;
        else if (flag == 3)
            return n1*n2;
        else if (flag == 4)
            return n1/n2;
        else if (flag == 5)
            return n1 << n2;
        else if (flag == 6)
            return n1 >> n2;
        else if (flag == 7)
            return n1 % n2;
        else if (flag == 8)
            return n1 & n2;
        else if (flag == 9)
            return n1 | n2;
        else if (flag == 10)
            return ~(n1 & n2);
        else if (flag == 11)
            return ~(n1 | n2);
        return n1^n2;

    } // end caculate


    // Hàm định dạng lại số cho dễ nhìn
    // Nếu fl truyền vào là true thì là ô đó là BIN hoặc HEX còn nếu là false thì ô đó là DEC hoặc OCT
    private String formatText(String s, boolean fl) {
        byte k;     // k là số số trong 1 cụm của text. VD: 0100 0011 0101 thì k =4;   12 345 678 thì k =3
                    // HEX vs BIN thì k=4;  DEC và OCT thì k=3
        if (fl) k = 4;
        else k = 3;
        boolean flagDauAm = false;

        StringBuilder fs = new StringBuilder();
        if (s.contains("-")) {
            flagDauAm = true;
            s = s.substring(1);
            fs.append("-");
        }

        int n, j, y;
        n = s.length();         // n là số chữ số
        j = n % k;              // j là số chữ só bị thừa ra khi cho k
        y = n/k;                // y là số cụm k chữ số đầy đủ

        fs.append(s.substring(0, j));
        for (int i=0; i<y; i++)
            fs.append(" " + s.substring(j+k*i, j+k+k*i));

        if (j == 0)
            if (flagDauAm) fs.deleteCharAt(1);
            else fs.deleteCharAt(0);

//        fs.append(s.substring(n));

        return fs.toString();
    }


    // Chuyển từ hexa về thập phân
    private long hex_to_dec(String str) {
        int n = str.length();
        Long tmp = 0l;
        for (int i=0; i<n; i++) {
            byte j = Byte.parseByte(Character.toString(str.charAt(i)), 16);
            tmp += (long)Math.pow(16, n-i-1) * j;
        }
        return tmp;
    }

    private long oct_to_dec(String str) {
        int n = str.length();
        Long tmp = 0l;
        for (int i=0; i<n; i++) {
            byte j = Byte.parseByte(Character.toString(str.charAt(i)), 16);
            tmp += (long)Math.pow(8, n-i-1) * j;
        }
        return tmp;
    }

    private long bin_to_dec(String str) {
        int n = str.length();
        Long tmp = 0l;
        for (int i=0; i<n; i++) {
            byte j = Byte.parseByte(Character.toString(str.charAt(i)), 16);
            tmp += (long)Math.pow(2, n-i-1) * j;
        }
        return tmp;
    }


    // set disable button HEX
    private void dis_hex() {
        binding.btnA.setEnabled(false);
        binding.btnB.setEnabled(false);
        binding.btnC.setEnabled(false);
        binding.btnD.setEnabled(false);
        binding.btnE.setEnabled(false);
        binding.btnF.setEnabled(false);
    }

    // set enable button HEX
    private void ena_hex() {
        binding.btnA.setEnabled(true);
        binding.btnB.setEnabled(true);
        binding.btnC.setEnabled(true);
        binding.btnD.setEnabled(true);
        binding.btnE.setEnabled(true);
        binding.btnF.setEnabled(true);
    }

    // set disable button DEC
    private void dis_dec() {
        binding.btn9.setEnabled(false);
        binding.btn8.setEnabled(false);
    }

    // set enable button DEC
    private void ena_dec() {
        binding.btn9.setEnabled(true);
        binding.btn8.setEnabled(true);
    }

    // set disable button OCT
    private void dis_oct() {
        binding.btn7.setEnabled(false);
        binding.btn6.setEnabled(false);
        binding.btn5.setEnabled(false);
        binding.btn4.setEnabled(false);
        binding.btn3.setEnabled(false);
        binding.btn2.setEnabled(false);
    }

    // set enable button OCT
    private void ena_oct() {
        binding.btn7.setEnabled(true);
        binding.btn6.setEnabled(true);
        binding.btn5.setEnabled(true);
        binding.btn4.setEnabled(true);
        binding.btn3.setEnabled(true);
        binding.btn2.setEnabled(true);
    }


    // Lấy chiều rộng màn hình
    public int getScreenWidth() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        return displaymetrics.widthPixels;
    } // end getScreenWidth
}