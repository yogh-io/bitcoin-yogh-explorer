package nl.yogh.wui.util;

public class ColorUtil {
  private static final float BRIGHTNESS = 0.8F;
  private static final float SAtURATION = 0.8F;

  public static int HSBtoRGB(final float hue, final float saturation, final float brightness) {
    int r = 0, g = 0, b = 0;
    if (saturation == 0) {
      r = g = b = (int) (brightness * 255.0f + 0.5f);
    } else {
      final float h = (hue - (float) Math.floor(hue)) * 6.0f;
      final float f = h - (float) java.lang.Math.floor(h);
      final float p = brightness * (1.0f - saturation);
      final float q = brightness * (1.0f - saturation * f);
      final float t = brightness * (1.0f - saturation * (1.0f - f));
      switch ((int) h) {
      case 0:
        r = (int) (brightness * 255.0f + 0.5f);
        g = (int) (t * 255.0f + 0.5f);
        b = (int) (p * 255.0f + 0.5f);
        break;
      case 1:
        r = (int) (q * 255.0f + 0.5f);
        g = (int) (brightness * 255.0f + 0.5f);
        b = (int) (p * 255.0f + 0.5f);
        break;
      case 2:
        r = (int) (p * 255.0f + 0.5f);
        g = (int) (brightness * 255.0f + 0.5f);
        b = (int) (t * 255.0f + 0.5f);
        break;
      case 3:
        r = (int) (p * 255.0f + 0.5f);
        g = (int) (q * 255.0f + 0.5f);
        b = (int) (brightness * 255.0f + 0.5f);
        break;
      case 4:
        r = (int) (t * 255.0f + 0.5f);
        g = (int) (p * 255.0f + 0.5f);
        b = (int) (brightness * 255.0f + 0.5f);
        break;
      case 5:
        r = (int) (brightness * 255.0f + 0.5f);
        g = (int) (p * 255.0f + 0.5f);
        b = (int) (q * 255.0f + 0.5f);
        break;
      }
    }
    return 0xff000000 | r << 16 | g << 8 | b << 0;
  }

  public static String getGreenRedSpectrum(final double confidence) {
    return Integer.toHexString(HSBtoRGB((float) ((1F - confidence) * 0.4F), SAtURATION, BRIGHTNESS)).substring(2);
  }
}
