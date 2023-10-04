package com.totterman.mvnspringboot3;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    private final VideoService videoService;
//    private final YouTube youTube;

    public HomeController(VideoService videoService /* , YouTube youTube */ ) {
        this.videoService = videoService;
//        this.youTube = youTube;
    }

/*    @GetMapping
    String index(Model model) {
        model.addAttribute("channelVideos",
//        https://www.youtube.com/channel/UCcSeeATlWJJbXpOZRYOfaDg/videos
youTube.channelVideos("UCcSeeATlWJJbXpOZRYOfaDg",
            //  youTube.channelVideos("UCjukbYOd6pjrMpNMFAOKYyw",
            10, YouTube.Sort.VIEW_COUNT));
        return "index";
    }
*/
    @GetMapping("/")
    public String index(Model model, Authentication authentication) {
        model.addAttribute("videos", videoService.getVideos());
        model.addAttribute("authentication", authentication);
        return "index";
    }

    @PostMapping("/new-video")
    public String newVideo(@ModelAttribute Video newVideo,
            Authentication authentication) {
        videoService.create(newVideo, authentication.getName());
        return "redirect:/";
    }

    @GetMapping("/react")
    public String react() {
        return "react";
    }

    @PostMapping("/multi-field-search")
    public String multiFieldSearch(@ModelAttribute VideoSearch search, Model model) {
        List<VideoEntity> searchResults = videoService.search(search);
        model.addAttribute("videos", searchResults);
        return "index";
    }

    @PostMapping("/search")
    public String universalSearch(@ModelAttribute UniversalSearch search,
            Model model,
            Authentication authentication) {
        List<VideoEntity> searchResults = videoService.search(search);
        model.addAttribute("search", search);
        model.addAttribute("videos", searchResults);
        model.addAttribute("authentication", authentication);
        return "index";
    }

    @PostMapping("/delete/videos/{videoId}")
    public String deleteVideo(@PathVariable Long videoId) {
        videoService.delete(videoId);
        return "redirect:/";
    }
}
