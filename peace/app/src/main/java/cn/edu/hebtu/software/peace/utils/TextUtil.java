package cn.edu.hebtu.software.peace.utils;

import android.widget.TextView;

public class TextUtil {
        private TextView tv;
        private String s;
        private int length;
        private long time;
        static int n = 0;
        private int nn;


        public TextUtil(TextView tv, String s, long time) {
            this.tv = tv;//textview
            this.s = s;//字符串
            this.time = time;//间隔时间
            this.length = s.length();
            startTv(n);//开启线程
        }


        public void startTv(final int n) {

            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            try {
                                final String stv = s.substring(0, n);//截取要填充的字符串
                                tv.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv.setText(stv);
                                    }
                                });
                                Thread.sleep(time);//休息片刻
                                nn = n + 1;//n+1；多截取一个
                                if (nn <= length) {//如果还有字，那么继续开启线程，相当于递归的感觉
                                    startTv(nn);
                                }

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }


                        }
                    }

            ).start();


        }


    }

