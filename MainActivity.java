package com.orphe.factory;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity {
    private final int NAVY = Color.rgb(5,9,26);
    private final int CARD = Color.rgb(16,26,58);
    private final int WHITE = Color.WHITE;
    private final int MUTED = Color.rgb(169,180,208);
    private final int BLUE = Color.rgb(22,191,255);
    private final int PURPLE = Color.rgb(139,92,255);
    private LinearLayout content;
    private EditText ideaInput;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(NAVY);
        getWindow().setNavigationBarColor(NAVY);
        showHome();
    }

    private TextView text(String s, float size, int color, boolean bold) {
        TextView t = new TextView(this);
        t.setText(s); t.setTextSize(size); t.setTextColor(color);
        t.setGravity(Gravity.CENTER_VERTICAL);
        if (bold) t.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        return t;
    }

    private GradientDrawable bg(int color, float radius) {
        GradientDrawable g = new GradientDrawable();
        g.setColor(color); g.setCornerRadius(radius);
        return g;
    }

    private Button button(String label) {
        Button b = new Button(this);
        b.setText(label); b.setTextColor(WHITE); b.setTextSize(15);
        b.setAllCaps(false); b.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        GradientDrawable g = new GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT,
                new int[]{BLUE, PURPLE});
        g.setCornerRadius(60);
        b.setBackground(g);
        b.setPadding(20, 8, 20, 8);
        return b;
    }

    private LinearLayout root() {
        LinearLayout r = new LinearLayout(this);
        r.setOrientation(LinearLayout.VERTICAL);
        r.setPadding(22, 18, 22, 18);
        r.setBackgroundColor(NAVY);
        return r;
    }

    private void showHome() {
        LinearLayout r = root();

        TextView brand = text("▶ IA ORPHE FACTORY", 23, WHITE, true);
        brand.setGravity(Gravity.CENTER);
        r.addView(brand, new LinearLayout.LayoutParams(-1, 54));

        TextView tag = text("Transformez vos idées en vidéos.", 18, BLUE, true);
        tag.setGravity(Gravity.CENTER);
        r.addView(tag, new LinearLayout.LayoutParams(-1, 42));

        TextView sub = text("Créez • Scénarisez • Donnez une voix • Sous-titrez • Exportez", 13, MUTED, false);
        sub.setGravity(Gravity.CENTER);
        r.addView(sub, new LinearLayout.LayoutParams(-1, 48));

        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(18, 18, 18, 18);
        card.setBackground(bg(CARD, 34));

        TextView q = text("Quelle vidéo voulez-vous créer ?", 20, WHITE, true);
        card.addView(q, new LinearLayout.LayoutParams(-1, 45));

        ideaInput = new EditText(this);
        ideaInput.setHint("Décrivez votre idée…");
        ideaInput.setHintTextColor(MUTED);
        ideaInput.setTextColor(WHITE);
        ideaInput.setTextSize(16);
        ideaInput.setGravity(Gravity.TOP);
        ideaInput.setMinLines(5);
        ideaInput.setPadding(16, 16, 16, 16);
        ideaInput.setBackgroundResource(com.orphe.factory.R.drawable.bg_input);
        LinearLayout.LayoutParams ip = new LinearLayout.LayoutParams(-1, 150);
        ip.setMargins(0, 10, 0, 12);
        card.addView(ideaInput, ip);

        Button generate = button("✨ Générer le projet");
        card.addView(generate, new LinearLayout.LayoutParams(-1, 58));
        generate.setOnClickListener(v -> showWorkflow());

        r.addView(card, new LinearLayout.LayoutParams(-1, 300));

        TextView demo = text("MODE DÉMONSTRATION", 12, PURPLE, true);
        demo.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams dp = new LinearLayout.LayoutParams(-1, 38);
        dp.setMargins(0, 16, 0, 0);
        r.addView(demo, dp);

        String[] labels = {"💡 Idée", "📝 Script", "🎬 Scènes", "🎙 Voix", "CC Sous-titres", "▶ Vidéo"};
        for (String l : labels) {
            TextView step = text(l, 15, WHITE, true);
            step.setPadding(16, 0, 16, 0);
            step.setBackground(bg(CARD, 22));
            LinearLayout.LayoutParams sp = new LinearLayout.LayoutParams(-1, 45);
            sp.setMargins(0, 5, 0, 5);
            r.addView(step, sp);
        }

        TextView foot = text("Version démo • Android • IA Orphe Factory", 11, MUTED, false);
        foot.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams fp = new LinearLayout.LayoutParams(-1, 35);
        fp.setMargins(0, 8, 0, 0);
        r.addView(foot, fp);

        ScrollView sv = new ScrollView(this);
        sv.setBackgroundColor(NAVY);
        sv.addView(r);
        setContentView(sv);
    }

    private void showWorkflow() {
        LinearLayout r = root();

        LinearLayout top = new LinearLayout(this);
        top.setGravity(Gravity.CENTER_VERTICAL);
        TextView title = text("IA ORPHE FACTORY", 21, WHITE, true);
        top.addView(title, new LinearLayout.LayoutParams(0, 52, 1));
        TextView back = text("Accueil", 14, BLUE, true);
        top.addView(back, new LinearLayout.LayoutParams(85, 52));
        back.setOnClickListener(v -> showHome());
        r.addView(top);

        String idea = ideaInput == null ? "" : ideaInput.getText().toString().trim();
        if (idea.isEmpty()) idea = "Une histoire inspirante qui commence par une idée.";

        TextView source = text("Votre idée\n" + idea, 15, WHITE, false);
        source.setPadding(16, 12, 16, 12);
        source.setBackground(bg(CARD, 20));
        r.addView(source, new LinearLayout.LayoutParams(-1, 90));

        String[] steps = {
                "1 / 6   💡 IDÉE", "2 / 6   📝 SCRIPT", "3 / 6   🎬 SCÈNES",
                "4 / 6   🎙 VOIX", "5 / 6   CC SOUS-TITRES", "6 / 6   ▶ VIDÉO"
        };
        for (int i=0;i<steps.length;i++) {
            TextView s = text(steps[i], 17, WHITE, true);
            s.setPadding(18, 0, 18, 0);
            s.setBackground(bg(CARD, 20));
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(-1, 58);
            p.setMargins(0, 8, 0, 0);
            r.addView(s, p);
            final int idx = i;
            s.setOnClickListener(v -> showStep(idx, idea));
        }

        TextView note = text("Touchez une étape pour voir la démonstration.", 13, MUTED, false);
        note.setGravity(Gravity.CENTER);
        r.addView(note, new LinearLayout.LayoutParams(-1, 50));

        ScrollView sv = new ScrollView(this);
        sv.setBackgroundColor(NAVY);
        sv.addView(r);
        setContentView(sv);
    }

    private void showStep(int idx, String idea) {
        LinearLayout r = root();
        TextView title = text("IA ORPHE FACTORY", 21, WHITE, true);
        title.setGravity(Gravity.CENTER);
        r.addView(title, new LinearLayout.LayoutParams(-1, 55));

        String[] names = {"💡 Idée", "📝 Script", "🎬 Scènes", "🎙 Voix", "CC Sous-titres", "▶ Vidéo"};
        TextView h = text(names[idx], 27, BLUE, true);
        h.setGravity(Gravity.CENTER);
        r.addView(h, new LinearLayout.LayoutParams(-1, 60));

        TextView body;
        if (idx == 0) {
            body = text("Idée reçue :\n\n" + idea + "\n\nL'application transforme cette idée en projet vidéo structuré.", 17, WHITE, false);
        } else if (idx == 1) {
            body = text("SCRIPT GÉNÉRÉ\n\nScène 1 — Le début\nPrésentation du personnage et de son objectif.\n\nScène 2 — Le défi\nLe personnage rencontre un obstacle.\n\nScène 3 — La transformation\nUne décision change la direction de l'histoire.\n\nScène 4 — La conclusion\nUne fin claire, émotionnelle et prête à tourner.", 16, WHITE, false);
        } else if (idx == 2) {
            body = text("SCÈNES GÉNÉRÉES\n\n🎞 Scène 1 — Introduction\n🎞 Scène 2 — Action\n🎞 Scène 3 — Moment fort\n🎞 Scène 4 — Conclusion\n\nChaque scène peut ensuite recevoir une image ou une vidéo générée.", 16, WHITE, false);
        } else if (idx == 3) {
            body = text("VOIX IA\n\nChoisissez une voix masculine ou féminine, un ton calme, dynamique ou émotionnel, puis ajustez la vitesse.\n\n▶ Aperçu audio (démo)", 16, WHITE, false);
        } else if (idx == 4) {
            body = text("SOUS-TITRES\n\n« Chaque grande vidéo commence par une idée. »\n\nPosition : Centre\nStyle : Premium\nTaille : Grande\n\nLes réglages sont simulés dans cette version de démonstration.", 16, WHITE, false);
        } else {
            body = text("APERÇU VIDÉO\n\n▶  00:00 ━━━━━ 00:45\n\nVotre vidéo est prête !\n\nCette APK est une démonstration de l'interface : les moteurs IA et l'export vidéo réel seront connectés dans la prochaine version.", 17, WHITE, false);
        }
        body.setPadding(20, 20, 20, 20);
        body.setBackground(bg(CARD, 24));
        LinearLayout.LayoutParams bp = new LinearLayout.LayoutParams(-1, 0, 1);
        bp.setMargins(0, 12, 0, 12);
        r.addView(body, bp);

        Button back = button("← Retour au workflow");
        r.addView(back, new LinearLayout.LayoutParams(-1, 58));
        back.setOnClickListener(v -> showWorkflow());

        ScrollView sv = new ScrollView(this);
        sv.setBackgroundColor(NAVY);
        sv.addView(r);
        setContentView(sv);
    }
}
