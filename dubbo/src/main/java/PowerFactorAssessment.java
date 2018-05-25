import java.io.Serializable;

/**
 * 描述：功率因数考核
 * <p>
 * 负数表示奖励，正数表示惩罚
 *
 * @author AngryApe
 * @date 2018-03-21
 */
public class PowerFactorAssessment implements Serializable{

    /**
     *
     */
    private String id;

    /**
     * 0：不考核、1：0.8、2：0.85、3：0.9
     */
    private String type;

    /**
     * 功率因数下限
     */
    private Double rangeFrom;

    /**
     * 功率因数上限
     */
    private Double rangeTo;

    /**
     * 电费调整比例表达式<br/>
     * (S-P)*0.15<br/>
     * S 表示功率因数标准，即下限,其值为{@link PowerFactorAssessment#rangeFrom }<br/>
     * P 表示实际的功率因数<br/>
     */
    private String formula;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getRangeFrom() {
        return this.rangeFrom;
    }

    public void setRangeFrom(Double rangeFrom) {
        this.rangeFrom = rangeFrom;
    }

    public Double getRangeTo() {
        return this.rangeTo;
    }

    public void setRangeTo(Double rangeTo) {
        this.rangeTo = rangeTo;
    }

    public String getFormula() {
        return this.formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

}