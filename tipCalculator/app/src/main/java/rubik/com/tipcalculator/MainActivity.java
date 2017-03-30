package rubik.com.tipcalculator;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class MainActivity extends AppCompatActivity {
    float  billAmount = 0;
    float percentage = 0;
    float tipAmount = 0;
    float totalBillAmount = 0;

    float percentageRegular = 10;
    float percentageNormal = 15;
    float percentageExcellent = 20;

    @BindView(R.id.etBillAmount)
    TextView etBillAmount;
    @BindView(R.id.tvTipPercent)
    TextView tvTipPercent;
    @BindView(R.id.tvTipAmount)
    TextView tvTipAmount;
    @BindView(R.id.tvBillTotalAmount)
    TextView tvBillTotalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
       //valida si la aplicación viene de pausa o stop
        //Importante tomar en cuenta que cuando se gira el telefono se pierden los datos y se
        //vuelve a ejecutar el onCreate
        if(savedInstanceState !=null){
          percentage =savedInstanceState.getFloat("percentage");
        }
        setTipValues();
    }

    private void setTipValues() {
        tvTipPercent.setText(getString(R.string.main_msg_tippercent,percentage));
        tvTipAmount.setText(getString(R.string.main_msg_tiptotal,tipAmount));
        tvBillTotalAmount.setText(getString(R.string.main_msg_billtotalresult,totalBillAmount));
    }

    @OnClick({R.id.ibRegularService, R.id.ibGoodService, R.id.ibExcellentService})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ibRegularService:
                percentage=percentageRegular;
                break;
            case R.id.ibGoodService:
                percentage = percentageNormal;
                break;
            case R.id.ibExcellentService:
                percentage = percentageExcellent;
                break;
        }
        calculateFinalBill();
        setTipValues();
    }



    @OnTextChanged(R.id.etBillAmount)
    public void onTextChanged(){
        calculateFinalBill();
        setTipValues();
    }

    private void calculateFinalBill() {
        if(percentage==0){
            percentage = percentageNormal;
        }
    if (!etBillAmount.getText().toString().equals("") && !etBillAmount.getText().toString().equals(".")) {

        billAmount = Float.valueOf(etBillAmount.getText().toString());
    }
        else{
            billAmount = 0;
        }

        tipAmount = (percentage*billAmount)/100;
        totalBillAmount = tipAmount+billAmount;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //Se guardan los datos si la aplicacion entra en suspencion o se gira la pantalla
        //Estos datos se utilizaran en el metodo onCreate para volver a cargarse en pantalla
        outState.putFloat("percentage",percentage);
        super.onSaveInstanceState(outState);
           }
}
