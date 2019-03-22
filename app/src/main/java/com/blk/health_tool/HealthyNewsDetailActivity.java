package com.blk.health_tool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.blk.R;
import com.blk.common.ToolBarSet;

import org.w3c.dom.Text;

public class HealthyNewsDetailActivity extends AppCompatActivity {

    private ImageView icon_back;
    private TextView news_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        ToolBarSet.setBar(window);
        setContentView(R.layout.healthy_news_detail);

        //初始化控件
        initView();
    }

    private void initView(){
        icon_back = (ImageView) findViewById(R.id.icon_back);
        icon_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        news_content = (TextView) findViewById(R.id.news_content);
        news_content.setText(Html.fromHtml("<p>&nbsp;之后我也研究了《黄帝内经》从头讲到脚，《求医不如求己》等，囫囵吞枣地知道了人体的12个经脉，“通则不痛，痛则不通”，也给自己刮过痧、泡过脚、吃过活泥鳅、站过桩、顺时针逆时针也揉过肚子和腰（带脉），给老公按摩过，还差一点就要去学针灸了。</p><p>&nbsp;然而因为心性不定，到2012年在法家上班后就不了了之，专研中医或者再深入中医的这个想法也越来越淡了，再加上刮痧、通经脉好疼也怪麻烦的，所以就不再玩了。 但身体作为一个系统有强大的修复能力，以及上医治未病的概念，在脑海中已被深深植入。命运再一次安排，让我继续链接到中医——营养学的这个分支，让我对大健康——预防健康有个更全面的认识。</p><p>引子：娟姐在2007—2011年机缘巧合和一位儿科中医师成为朋友（她是我的房东），她给我讲解了人体有自动而强大修复功能的概念；她说：人体系统就类似钟表，它会自动地不停地循环，把吃进去的好东西转化为营养和能量，把废物再自动代谢排出体外。但如果一旦钟表停了，我们要做的是用药（或者在医院）把这个停止的“因”找出来，消除掉；或者用把力帮助钟表度过这个卡点。但根本是得让钟表循环起来，而不是一直用手拨着钟表转。</p><p>所以在这里和大家概括性地分享下大健康的概念，目的在于我们自己来用科学【而非经验或者模仿】来指导自己的健康，用预防【而非医院或者药物】来掌控自己的身体，如此，让我们的生命能量能够提升，生活质量能够更好。</p><p>\u200D 【人生真的很难得，活或玩出高质量高版本的自我生活才不枉此生】</p><h1>人体是由什么组成的？ </h1><p>人体极其复杂，但其实也是借由一个个细胞通过一些粘着物粘在一起而成。</p><p>这就像砖盖大楼，不管大楼多么宏伟壮观，结构多么复杂，也是由一块块砖头通过水泥和砂石等粘在一起而垒起来的，而大楼中的钢筋水泥框框架更像人体的骨骼。细胞则和砖头在形状上很像，排列也有很多相似之处。有的细胞扁扁的，排列起来活像地面上的瓷砖；有的细胞立方体，排列起来围成管状，活像用砖砌起来的下水道，有的细胞是柱状，有的是球形，还有不规则的；形状各异都是为了满足身体不同部位和功能的需要。</p><p>&nbsp;砖 ——》墙 ——》屋子 ——》一层楼 ——》大楼 内环境：砖头、水泥、砂石，以及大楼的空气、环境等。</p><p>&nbsp;细胞——》组织 ——》器官 ——》系统 ——》人体（移动的大楼） 内环境：细胞与细胞之间的空隙，细胞和血液之间进行交换物质的场所。</p><p>&nbsp;大楼里有电线水管 =人体有血管； 大楼里有暖气管下水道 =人体有肠胃道；大楼里有各式各样的活动需要用品以及相关的工作人员，而人体内也一样。</p><p>&nbsp;这么说来，人体就是一个移动的大楼，里面有主体结构（细胞组成），水电气管道（细胞组成），有硬件设施（细胞组成），有负责各式各样不同职能的员工（细胞）。</p><p>&nbsp;所以，一座大楼是否高大坚固，运作是否良好，以及有没有或者能不能发挥最大的功效，就需要有数量够多、质量够好、存储够多的原材料（砖头，砂，水泥，钢筋，工具等）；给够原材料，那么大楼里的人自然会去做很多事情，创造很多美好的经历；而对于人体来说，人体系统就像一个化工厂，也在不停地吸收和代谢，合成和分解。</p><p><strong>&nbsp;对于人体的健康来说，就是细胞的数量够不够多/质量够不够好（营养均衡），够不够有活力（适当的运动），够不够通透明快（喜悦的心情）以及在睡眠中修复得够不够好（充足的睡眠）。 </strong></p><p>细胞的原材料（或者说人体）的构成大约为：蛋白质（10~15%），脂肪（5~10%），碳水化合物（10~15%），维生素（4%），矿物质（1%），水（60~70%）。</p><p>人体有60-70亿兆细胞，如果一个人非常健康，他的每一个细胞都很有活力；就跟大楼每一块砖头都很坚固饱满发亮一样。</p><p>如何在这个不断代谢循环系统中让细胞依旧健康，这就需要给细胞补充足够以及充分的原材料，也就是水\\蛋白质\\脂肪\\碳水化合物\\维生素\\矿物质。</p><p><strong>所以，亲们，我们吃饭是为了不饿，更是为了给细胞补充原材料，你们认同吗？</strong></p><p>所以我们可以讨论以下几个问题：</p><h2>&nbsp;一、我们的一日三餐是否给细胞补充够了原材料？ </h2><p>很多人认为我每日三餐吃得很好，我吃很多新鲜蔬菜水果，也按时按量吃饭。如果真的能做到，恭喜你！或许你可以健康，毕竟细胞60-70亿呢，有一部分营养不那么充分我们还是不会有要去医院看病的问题，再加上我们本身人体也有大量的储备（比如两个肺，两个肾就是储备，本来日常半个就够用了）。</p><p>&nbsp;但娟姐认为这其实还不够完全健康（对于追求健康长寿有活力的人来说），为什么呢？</p><p>（1）从目前食品的安全以及生态链来讲，现在的鸡肉、猪肉、鱼等已经不再像我们小时候那样有优质的营养了，大量的土壤也被污染，激素农药的滥用，甚至有可能吃进去后营养素没有吃够，还吃进了一堆给身体肝脏带来负担的毒素。</p><p>（2）人体的活动远比以前更多了，现代人的各个层面危机、焦虑感、各种思虑、不良的作息、停不下来的手机刷屏，这些都需要细胞在支持运作，耗费能量。而日出而作，日落而息的作息方式离我们越来越远，包括对老人家和小孩子都一样，这种方式也让人体在该修复的时候没有得到好好的修复（晚间8点-12点）。</p><p>&nbsp;（3）矿物质维生素或者微量元素【辅酶】的摄取量不够。美国营养协会其实已经建议每日的食谱增加矿物质维生素的补充。因为矿物质（微量元素）并不能内部合成，而需要从食物中摄取，而食物链的破坏让绝大多数的我们摄取量不够。</p><p>微量元素（矿物质维生素）在人体的作用相当于催化剂（或者点火器），如果没有足够的微量元素，人体这个化工厂的许多反应并不能发生；这就是所谓的酵素或者辅酶的重要性。</p><h2>&nbsp;二、亚健康是不是疾病？如何改善？</h2><p>&nbsp;娟姐的答案：是的！ 亚健康是疾病的初期症状，只是因为人体有足够的储存以及人体有足够的耐力依旧让你能够活下来，只是不那么有活力地活着。</p><p>就好像这个大楼虽然里面千疮百孔，偶尔会停电，下水道会堵或者下水道下水很慢了，但是大楼还不会倒塌，还能有人待得下去。</p><p>&nbsp;但是如果不及时修理，不好好修缮，不好好维护，那么下水道可能就会彻底堵了，亚健康的累积就会形成疾病。结果就是去医院寻求医生的整治。</p><p>&nbsp;如果这样的话，其实很可惜，因为大楼里本来就有武艺高超的水电工，下水道清理工，地面清洁工，等等；每一个人都非常尽职尽责。只是您没有给对给够人家原材料，没有给对给够工具，他们也无能为力。 而且人体这个系统非常聪明，他一定是保证生命器官的优先，</p><p>【生命器官】人体的五大生命器官：心，脑，肺，肾，肝。生命器官的意思就是没有他们就活不了，所以对人体这个系统来说，一定是保护这五大器官为优先，美不美观（健不健康）是下一步的事情；也就是说如果给的原材料不够，人体一定先保住生命器官，再不够就会从墙上去拆下砖来把司令部保住。</p><h2>&nbsp;三、内环境和外环境的影响 </h2><p>内环境是指细胞与细胞之间的空隙，我们的吸收和代谢也是在内环境中去提取的，就好像大楼中的环境。只有大楼里的空气清晰，5S很整洁，干净舒适，大楼里工作的人才会更加开心和高效。人体内环境也是一个道理。</p><p>&nbsp;外环境对于人体来说，就是皮肤以外，所接触到、所呼吸到、所感知到的所有事物。我们平时用的化妆品，接触到的所有东西，和皮肤接触的衣物，呼吸的空气，喝进去的水，或者和人接触所带来的情绪波动，等等。</p><p>这些对人体或者对人体的细胞其实都是有影响，都会或多或少影响到人体细胞的能量以及活力。这么说吧，就是会影响到大楼里的员工积极性高不高，愿不愿意开开心心干活，但是您放心，细胞和员工还不太一样，只要给细胞足够的原料，他就会尽心尽责。</p><p>&nbsp;好了，关于预防健康的一小小块，娟姐把这两年来看的书大概整理了下，后续会有更多的分享，更细节的分享，希望对你有用，也希望这些概括性科学知识能帮助到合理应用营养素，也就是食补，进而远离医院。</p><p><strong>把健康把握在自己的手里，让自己和家人远离医院。这就是我宣传大健康的初衷。谢谢观赏！ </strong></p>\n" ));
    }

    @Override
    //安卓重写返回键事件
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return true;
    }

}
