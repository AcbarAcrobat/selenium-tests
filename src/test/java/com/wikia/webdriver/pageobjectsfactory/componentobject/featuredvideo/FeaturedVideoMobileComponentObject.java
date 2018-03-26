package com.wikia.webdriver.pageobjectsfactory.componentobject.featuredvideo;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FeaturedVideoMobileComponentObject extends WikiBasePageObject{

  private static final String AUTOPLAY_COOKIE = "featuredVideoAutoplay";

  @FindBy(css = ".jw-video")
  private WebElement featuredVideo;

  @FindBy(css = ".wikia-jw-settings-button")
  private WebElement settingsMenu;

  @FindBy(css = ".jw-title-primary")
  private WebElement primaryTitle;

  @FindBy(css = ".article-featured-video__on-scroll-video-wrapper")
  private WebElement player;

  @FindBy (css = ".article-featured-video__attribution-link")
  private WebElement attributionLink;

  @FindBy (css = ".article-featured-video__attribution-avatar")
  private WebElement attributionAvatar;

  @FindBy(css = ".jw-controlbar")
  private WebElement controlbar;

  @FindBy(css = "*[aria-label='Start playback']")
  private WebElement playButton;

  @FindBy(css = "*[aria-label='Pause']")
  private WebElement pauseButton;

  @FindBy(css = ".jw-state-playing")
  private WebElement playerStatePlaying;

  @FindBy(css = ".jw-state-paused")
  private WebElement playerStatePaused;

  @FindBy(css = ".wikia-jw-settings-button")
  private WebElement videoQualityButton;

  @FindBy(css = ".wds-toggle__label")
  private WebElement autoplayToggle;

  @FindBy(css = ".jw-icon-tooltip.jw-icon-volume.jw-off")
  private WebElement volumeMuted;

  @FindBy(css = ".wikia-jw-settings__list .wikia-jw-settings__captions-button")
  private WebElement videoCaptionsButton;

  @FindBy(css = ".wikia-jw-settings__submenu")
  private List<WebElement> videoSettingsSubmenu;

  public FeaturedVideoMobileComponentObject setAutoplayCookie(boolean autoplay) {
    driver.manage().addCookie(new Cookie(
        AUTOPLAY_COOKIE,
        autoplay ? "1" : "0",
        String.format(".%s", Configuration.getEnvType().getWikiaDomain()),
        null,
        null
    ));

    driver.navigate().refresh();

    return this;
  }

  public FeaturedVideoMobileComponentObject openWikiArticle(String articleName) {
    this.openWikiPage(getWikiUrl() + articleName + "?noads=1");

    return this;
  }

  public boolean isFeaturedVideoDisplayed() {
    wait.forElementVisible(featuredVideo);

    return featuredVideo.isDisplayed();
  }

  public String getTitle() {
    wait.forElementVisible(primaryTitle);

    return primaryTitle.getText();
  }

  public boolean isAttributionLinkVisible () {
    wait.forElementVisible(attributionLink);

    return attributionLink.isDisplayed();
  }

  public boolean isAttributionLinkNotVisible () {
    wait.forElementNotVisible(attributionLink);

    return true;
  }
  public boolean isAttributionAvatarVisible () {
    wait.forElementVisible(attributionAvatar);

    return attributionAvatar.isDisplayed();
  }


  public FeaturedVideoMobileComponentObject activatePlayerOptions() {
    wait.forElementClickable(player);
    player.click();

    return this;
  }

  public FeaturedVideoMobileComponentObject clickPlay() {
    wait.forElementClickable(playButton);
    playButton.click();

    try {
      Thread.sleep(5000);
    }catch (InterruptedException e){

    }

    return this;
  }

  public FeaturedVideoMobileComponentObject clickPause() {
    wait.forElementClickable(pauseButton);
    pauseButton.click();

    return this;
  }

  public boolean isVideoPlaying() {
    wait.forElementVisible(playerStatePlaying);

    return playerStatePlaying.isDisplayed();
  }

  public boolean isVideoPaused() {
    wait.forElementVisible(playerStatePaused);

    return playerStatePaused.isDisplayed();
  }


  public boolean isAutoplayOn() {

    return "true".equals(autoplayToggle.getAttribute("checked"));
  }

  public FeaturedVideoMobileComponentObject showControlBar() {
    wait.forElementClickable(player);

    return this;
  }

  public FeaturedVideoMobileComponentObject openSettingsMenu() {

    wait.forElementClickable(settingsMenu)
        .click();

    return this;
  }

  public FeaturedVideoMobileComponentObject openQualityMenu() {
    wait.forElementClickable(videoQualityButton)
        .click();

    return this;
  }

  public FeaturedVideoMobileComponentObject openCaptionsMenu() {
    wait.forElementClickable(videoCaptionsButton)
        .click();

    return this;
  }

  public boolean isVolumeMuted() {
    wait.forElementClickable(volumeMuted);
    return volumeMuted.isEnabled();
  }

  public boolean isQualityAvailable() {

    return wait.forTextInElement(videoSettingsSubmenu, 0, "Auto");

  }

  public boolean areCaptionsAvailable() {
    By last = By.xpath("//*[@data-track='0']");
    return wait.forTextInElement(last, "No captions");
  }

}
